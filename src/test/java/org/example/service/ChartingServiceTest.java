package org.example.service;

import org.example.model.Charting;
import org.junit.jupiter.api.BeforeEach;
import freemarker.template.Configuration;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import javax.management.RuntimeErrorException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ChartingServiceTest {

    private ChartingService chartingService;
        private Configuration freemarkerCfg;

    @BeforeEach
    void setup() throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:duckdb:myDatabase.db");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(new SingleConnectionDataSource(conn, true));
        chartingService = new ChartingService(jdbcTemplate, freemarkerCfg);
    }

    @Test
    void testWidgetW1() {
        Map<String, String> param = new HashMap<>();
        param.put("widgetId", "w1");
        param.put("age", "20");
        Charting charting = chartingService.getChartData(param);
        Map<String, List<Object>> result = charting.chartData();

        assertNotNull(result);
        assertTrue(result.containsKey("gender"));
        assertTrue(result.containsKey("avg_height"));
    }

    @Test
    void testWidgetW2() {
        Map<String, String> param = new HashMap<>();
        param.put("widgetId", "w2");
        Charting charting = chartingService.getChartData(param);
        Map<String, List<Object>> result = charting.chartData();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.containsKey("gender"));
        assertTrue(result.containsKey("avg_age"));
    }

    @Test
    void testWidgetW3() {
        Map<String, String> param = new HashMap<>();
        param.put("widgetId", "w3");
        param.put("age", "20");
        Charting charting = chartingService.getChartData(param);
        Map<String, List<Object>> result = charting.chartData();

        assertNotNull(result);
        assertTrue(result.containsKey("gender"));
        assertTrue(result.containsKey("avg_height"));
        assertTrue(result.containsKey("avg_age"));
        assertTrue(result.containsKey("count"));
    }

    @Test
    void testWithMissingAgeParamForW3() {
        Map<String, String> param = new HashMap<>();
        param.put("widgetId", "w3");
        Charting charting = chartingService.getChartData(param);
        Map<String, List<Object>> result = charting.chartData();

        assertNotNull(result);
        assertTrue(result.containsKey("gender"));
        assertTrue(result.containsKey("avg_height"));
        assertTrue(result.containsKey("avg_age"));
        assertTrue(result.containsKey("count"));
    }

    @Test
    void testWithMissingWidgetId() {
        Map<String, String> params = new HashMap<>();
        params.put("age", "20");
        Charting result = chartingService.getChartData(params);

        assertNull(result);
    }

    @Test
    void testWithInvalidWidgetId() {
        Map<String, String> params = new HashMap<>();
        params.put("widgetId", "invalidWId");
        params.put("age", "20");
        Charting result = chartingService.getChartData(params);

        assertNotNull(result);
        assertTrue(result.chartData().isEmpty());
    }
}