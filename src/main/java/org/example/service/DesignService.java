package org.example.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.ChartingDesign;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Service
public class DesignService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public ChartingDesign getDesign(String widgetId) {
        try{
            InputStream is = getClass().getResourceAsStream("/design/design.json");
            if (is != null) {
                Map<String, ChartingDesign> allDesigns = objectMapper.readValue(is, new TypeReference<>() {});
                return allDesigns.get(widgetId);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading design data", e);
        }

        return null;
    }
}
