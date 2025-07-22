package config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DuckDBInitializer {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public DuckDBInitializer(JdbcTemplate jdbc) {
        this.jdbcTemplate = jdbc;
    }

    @PostConstruct
    public void init() {
        try {
            jdbcTemplate.execute("DROP TABLE IF EXISTS person");
            jdbcTemplate.execute("DROP TABLE IF EXISTS widget_queries");
            jdbcTemplate.execute("DROP TABLE IF EXISTS widget_design");
            jdbcTemplate.execute("DROP TABLE IF EXISTS widget_parameters");

            // Create person table
            jdbcTemplate.execute("""
                        CREATE TABLE person (
                            id INTEGER,
                            name TEXT,
                            gender TEXT,
                            age INTEGER,
                            height DOUBLE
                        )
                    """);

            // Insert data
            jdbcTemplate.execute("""
                        INSERT INTO person (id, name, gender, age, height) VALUES
                        (1, 'Shivam', 'Male', 20, 5.9),
                        (2, 'Sneha', 'Female', 20, 5.0),
                        (3, 'Aman', 'Male', 22, 6.0),
                        (4, 'Priya', 'Female', 23, 5.4),
                        (5, 'Raja', 'Male', 18, 5.6),
                        (6, 'Sapna', 'Female', 31, 5.5)
                    """);

            // Create widget_queries table
            jdbcTemplate.execute("""
                        CREATE TABLE widget_queries (
                            widget_id TEXT,
                            sql_query TEXT,
                        )
                    """);

            jdbcTemplate.execute("""
                        INSERT INTO widget_queries (widget_id, sql_query) VALUES
                        ('w1', 'SELECT gender, AVG(height) AS avg_height FROM person WHERE age > ${(age?number)!10} GROUP BY gender'),
                        ('w2', 'SELECT gender, AVG(age) AS avg_age FROM person GROUP BY gender'),
                        ('w3', 'SELECT gender, AVG(height) AS avg_height, AVG(age) AS avg_age, COUNT(*) AS count FROM person WHERE age > ${(age?number)!10} GROUP BY gender')
                    """);

            // Create widget_design table
            jdbcTemplate.execute("""
                        CREATE TABLE widget_design (
                            widget_id TEXT,
                            x_axis_field TEXT,
                            y_axis_field TEXT,
                            chart_type TEXT,
                            bar_color TEXT,
                            width INTEGER,
                            height INTEGER,
                            x_axis_label TEXT,
                            y_axis_label TEXT,
                            show_grid BOOLEAN
                        );
                    """);

            jdbcTemplate.execute("""
                        INSERT INTO widget_design (
                            widget_id,
                            x_axis_field,
                            y_axis_field,
                            chart_type,
                            bar_color,
                            width,
                            height,
                            x_axis_label,
                            y_axis_label,
                            show_grid
                        ) VALUES
                            ('w1', 'gender', 'avg_height', 'pie', '#4caf50', 900, 500, 'Gender', 'Avg Height', false),
                            ('w2', 'gender', 'avg_age', 'line', '#f44336', 900, 500, 'Gender', 'Avg Age', false),
                            ('w3', 'gender', 'values', 'bar', '#2196f3', 900, 500, 'Gender', 'Values', false),
                    """);

            System.out.println("=> Running DuckDBInitializer...");
        } catch (Exception e) {
            System.err.println("Error initializing DuckDB:");
            e.printStackTrace();
        }
    }
}