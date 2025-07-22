package org.example.model;

import java.util.List;

public record Charting(String widgetId, List<String> x, List<Dataset> datasets) {}