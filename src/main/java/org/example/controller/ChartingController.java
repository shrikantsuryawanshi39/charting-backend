package org.example.controller;

import org.example.model.Charting;
import org.example.service.ChartingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class ChartingController {

    private final ChartingService chartingService;

    @Autowired
    public ChartingController(ChartingService chartingService) {
        this.chartingService = chartingService;
    }
    @GetMapping("/orgs/{orgId}/widgets/{widgetId}")
    public ResponseEntity<Charting> getChartData(
            @PathVariable String widgetId,
            @RequestParam Map<String, String> inputParams,
            @PathVariable Long orgId
    ) {
        Map<String, String> data = new HashMap<>();
        data.put("widgetId", widgetId);
        data.put("orgId", String.valueOf(orgId));
        data.putAll(inputParams);

        Charting charting = chartingService.getChartData(data);

        if (charting == null || charting.chartData().isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(charting);
    }
}