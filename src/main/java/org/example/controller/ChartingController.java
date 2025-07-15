package org.example.controller;

import org.example.model.Charting;
import org.example.service.ChartingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class ChartingController {
    private final ChartingService chartingService;

    @Autowired
    public ChartingController(ChartingService chartingService) {
        this.chartingService = chartingService;
    }

    @GetMapping("/charting/{widgetId}")
    public Charting getChartData(@PathVariable String widgetId) {
        return chartingService.getChartData(widgetId);
    }
}
