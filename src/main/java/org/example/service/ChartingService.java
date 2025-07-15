package org.example.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Charting;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Service
public class ChartingService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public Charting getChartData(String widgetId) {
        try{
            InputStream is = getClass().getResourceAsStream("/charting/charting.json");
            if (is != null) {
                Map<String, Charting> allData = objectMapper.readValue(is, new TypeReference<>() {});
                return allData.get(widgetId);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading charting data", e);
        }

        return null;
    }
}
