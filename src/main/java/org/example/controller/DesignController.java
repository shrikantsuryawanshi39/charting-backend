package org.example.controller;

import org.example.model.ChartingDesign;
import org.example.service.DesignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class DesignController {

    private final DesignService designService;

    @Autowired
    public DesignController(DesignService designService) {
        this.designService = designService;
    }

    @GetMapping("/design/{widgetId}")
    public ChartingDesign getDesign(@PathVariable String widgetId) {
        return designService.getDesign(widgetId);
    }
}
