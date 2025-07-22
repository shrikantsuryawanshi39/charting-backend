package org.example.model;

import java.util.List;
import java.util.Map;

public record Charting(Map<String, List<Object>> chartData) {}