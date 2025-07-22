package org.example.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ChartingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getChartDataWithParams() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get("/api/orgs/2/widgets/w1")
                        .param("age", "19"))
                .andExpect(status().isOk()) //200
                .andReturn()
                .getResponse();
    }

    @Test
    void getChartDataWithoutParams() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get("/api/orgs/2/widgets/w1"))
                .andExpect(status().isOk()) //200
                .andReturn()
                .getResponse();
    }

    @Test
    void getChartDataInvalidWidgetId() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get("/api/orgs/2/widgets/invalidWId"))
                .andExpect(status().isNotFound()) //404
                .andReturn()
                .getResponse();
    }
}
