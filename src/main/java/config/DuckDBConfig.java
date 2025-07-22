package config;

import org.duckdb.DuckDBDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DuckDBConfig {

    @Bean
    public DataSource dataSource() throws SQLException {
        return new SimpleDriverDataSource(new DuckDBDriver(), "jdbc:duckdb:mydatabase.db");
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
