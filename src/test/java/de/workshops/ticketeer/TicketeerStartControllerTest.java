package de.workshops.ticketeer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TicketeerStartControllerTest {

    @Autowired
    MockMvc mockMvc;


    @Test
    void testPingOk() throws Exception {
        mockMvc
                .perform(get("/api/ping"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(is("{\"status\": \"ticketeer-ok\"}")))
                .andExpect(jsonPath("$.status", is("ticketeer-ok")));
    }
}