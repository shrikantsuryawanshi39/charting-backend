package org.example.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Charting;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Service
public class ChartingService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public Charting getChartData(String widgetId) {
        String path = "/charting/charting.json";
        try{
            InputStream is = getClass().getResourceAsStream(path);
            if (is != null) {
                Map<String, Charting> map = objectMapper.readValue(is, new TypeReference<Map<String, Charting>>() {});
                return map.get(widgetId);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading charting data", e);
        }
        return new Charting(widgetId, List.of(), List.of());
    }


}
