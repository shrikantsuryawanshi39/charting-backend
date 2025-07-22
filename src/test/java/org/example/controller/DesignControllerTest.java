package org.example.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DesignControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getDesignTest() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get("/api/orgs/2/designs/w1"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
    }

    @Test
    void getDesignWithInvalidWidgetIdTest() throws Exception {
        mockMvc.perform(get("/api/orgs/2/designs/invalidWId"))
                .andExpect(status().isNotFound());
    }

}
