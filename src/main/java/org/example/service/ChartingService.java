package org.example.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.example.model.Charting;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.*;

@Service
public class ChartingService {
    private final JdbcTemplate jdbc;
    private final Configuration freemarkerCfg;

    private static final String GET_DATA_QUERY = "SELECT sql_query FROM widget_queries WHERE widget_id = ?";

    public ChartingService(JdbcTemplate jdbc, Configuration freemarkerCfg) {
        this.jdbc = jdbc;
        this.freemarkerCfg = freemarkerCfg;
    }

    public Charting getChartData(Map<String, String> parameters) {
        String widgetId = parameters.get("widgetId");
        if (widgetId == null || widgetId.isBlank()) { System.err.println("Missing widgetId parameter."); return null; }

        Map<String, List<Object>> result = new LinkedHashMap<>();

        try {
            String rawTemplateQuery = jdbc.queryForObject(GET_DATA_QUERY, String.class, widgetId);

            Template template = new Template("sqlTemplate", new StringReader(rawTemplateQuery), freemarkerCfg);
            StringWriter writer = new StringWriter();
            template.process(parameters, writer);

            String finalQuery = writer.toString();
            List<Map<String, Object>> rows = jdbc.queryForList(finalQuery);

            if (!rows.isEmpty()) {
                for (String key : rows.get(0).keySet()) {
                    List<Object> columnValues = new ArrayList<>();
                    for (Map<String, Object> row : rows) {
                        columnValues.add(row.get(key));
                    }
                    result.put(key, columnValues);
                }
            }
        } catch (EmptyResultDataAccessException e) {
            System.err.println("No entry found in widget_queries for widgetId: " + widgetId);
        } catch (Exception e) {
            System.err.println("Error processing SQL template or fetching data: " + e.getMessage());
            e.printStackTrace();
        }
        return new Charting(result);
    }
}