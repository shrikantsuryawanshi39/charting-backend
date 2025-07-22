package org.example.controller;

import org.example.model.Design;
import org.example.service.DesignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/orgs/{orgId}/designs/{widgetId}")
    public ResponseEntity<Design> getDesign(@PathVariable String widgetId) {
        Design design = designService.getDesign(widgetId);
        if (design == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(design);
    }
}
