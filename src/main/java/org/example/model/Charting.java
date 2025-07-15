package org.example.model;

import java.util.List;

public record Charting(
        String widgetId,
        List<?> x,
        List<?> y,
        List<?> performance
) {}