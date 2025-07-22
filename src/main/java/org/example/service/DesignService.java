package org.example.service;

import org.example.model.Design;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DesignService {

    private final JdbcTemplate jdbc;
    public static final String GET_DESIGN_QUERY = "SELECT * FROM widget_design WHERE widget_id = ?";

    public DesignService(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Design getDesign(String widgetId) {
        try {
            return jdbc.queryForObject(GET_DESIGN_QUERY, new Object[]{widgetId}, (rs, rowNum) ->
                    new Design(
                            rs.getString("widget_id"),
                            rs.getString("x_axis_field"),
                            rs.getString("y_axis_field"),
                            rs.getString("chart_type"),
                            rs.getString("bar_color"),
                            rs.getInt("width"),
                            rs.getInt("height"),
                            rs.getString("x_axis_label"),
                            rs.getString("y_axis_label"),
                            rs.getBoolean("show_grid")
                    )
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
