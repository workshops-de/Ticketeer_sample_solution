package de.workshops.ticketeer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.client.RestTestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/// Here we show 3 possibilities to test controllers with SpringBoot provided test support.
/// - with MockMvc
/// - with MockMvcTester
/// - with RestTestClient
///
/// In all 3 cases no dependency to the tested controller class is needed.
/// This is because the @SpringBootTest annotation creates a TestApplicationContext before the test is executed.
/// The TestApplicationContext contains all beans that are configured in the application context.
///
/// `@AutoConfigureMockMvc` annotation is needed, when we want to use MockMvc or MockMvcTester.
///
/// `@AutoConfigureRestTestClient` annotation is needed, when we want to use RestTestClient.
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestTestClient
class TicketeerStartControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    MockMvcTester mockMvcTester;

    @Autowired
    RestTestClient restTestClient;

    // MockMvc relies on Hamcrest assertions
    @Test
    void testPingOk_withMockMvc() throws Exception {
        mockMvc
                .perform(get("/api/ping"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(content().string(is("{\"status\": \"ticketeer-ok\"}")))
                .andExpect(jsonPath("$.status", is("ticketeer-ok")));
    }

    // MockMvcTester offers use of assertJ assertions
    @Test
    void testPingOk_withMockMvcTester() {
        assertThat(mockMvcTester.get().uri("/api/ping"))
                .hasStatusOk()
                .hasContentTypeCompatibleWith(MediaType.TEXT_PLAIN)
                .bodyJson().extractingPath("$.status").isEqualTo("ticketeer-ok");
    }

    @Test
    void testPingOk_withRestTestClient() {
        restTestClient.get().uri("/api/ping").exchange()
                .expectStatus().isOk()
                .expectHeader().contentTypeCompatibleWith(MediaType.TEXT_PLAIN)
                .expectBody().jsonPath("$.status").isEqualTo("ticketeer-ok");
    }
}