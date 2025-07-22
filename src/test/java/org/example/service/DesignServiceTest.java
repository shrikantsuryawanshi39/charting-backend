package org.example.service;

import org.example.model.Design;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DesignServiceTest {

    private DesignService designService;

    @BeforeEach
    void setup() throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:duckdb:myDatabase.db");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(new SingleConnectionDataSource(conn, true));
        designService = new DesignService(jdbcTemplate);
    }

    @Test
    void testWidgetW1Design() {
        Design result = designService.getDesign("w1");

        assertEquals("w1", result.widgetId());
        assertEquals("gender", result.xAxisField());
        assertEquals("avg_height", result.yAxisField());
        assertEquals("pie", result.chartType());
    }

    @Test
    void testWidgetW2Design() {
        Design result = designService.getDesign("w2");

        assertEquals("w2", result.widgetId());
        assertEquals("gender", result.xAxisField());
        assertEquals("avg_age", result.yAxisField());
        assertEquals("line", result.chartType());
    }

    @Test
    void testWidgetW3Design() {
       Design result = designService.getDesign("w3");

        assertEquals("w3", result.widgetId());
        assertEquals("gender", result.xAxisField());
        assertEquals("values", result.yAxisField());
        assertEquals("bar", result.chartType());
    }

    @Test
    void getDesignTestWithInvalidWId() {
        Design result = designService.getDesign("invalidId");
        assertNull(result);
    }
}
