package org.example.model;

public record ChartingDesign(
        String widgetId,
        String chartType,
        String xAxisField,
        String yAxisField,
        String performanceField,
        String barColor,
        int width,
        int height,
        String xAxisLabel,
        String yAxisLabel,
        boolean showGrid,
        String fontFamily,
        String legendPosition,
        boolean animation
) {}
