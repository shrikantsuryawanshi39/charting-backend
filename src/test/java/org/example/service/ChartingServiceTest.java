import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChartingService {

    private final JdbcTemplate jdbcTemplate;

    public ChartingService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Map<String, List<Object>> getChartData(String widgetId) {
        String sqlQuery = jdbcTemplate.queryForObject(
                "SELECT sql_query FROM widget_queries WHERE widget_id = ?",
                String.class,
                widgetId
        );

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlQuery);
        Map<String, List<Object>> result = new LinkedHashMap<>();

        if (!rows.isEmpty()) {
            for (String key : rows.get(0).keySet()) {
                result.put(key, new ArrayList<>());
            }
            for (Map<String, Object> row : rows) {
                for (Map.Entry<String, Object> entry : row.entrySet()) {
                    result.get(entry.getKey()).add(entry.getValue());
                }
            }
        }

        return result;
    }
}
