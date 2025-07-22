package org.example.model;
public record Design(
        String widgetId,
        String xAxisField,
        String yAxisField,
        String chartType,
        String barColor,
        int width,
        int height,
        String xAxisLabel,
        String yAxisLabel,
        boolean showGrid
) {}
