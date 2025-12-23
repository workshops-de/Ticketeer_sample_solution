package de.workshops.ticketeer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.client.RestTestClient;

/// Here we show 3 possibilities to test controllers with SpringBoot provided test support.
/// - with MockMvc
/// - with MockMvcTester
/// - with RestTestClient
///
/// In all 3 cases no direct instantiation of the tested controller class is needed. This is because
/// the `@WebMvcTest` annotation creates a minimal web-layer ApplicationContext before the test is
/// executed, registering only the specified controllers and related MVC infrastructure rather than
/// all application beans.
///
/// `@AutoConfigureMockMvc` annotation is needed, when we want to use MockMvc or MockMvcTester.
///
/// `@AutoConfigureRestTestClient` annotation is needed, when we want to use RestTestClient.
@WebMvcTest(controllers = StartController.class)
@AutoConfigureMockMvc
@AutoConfigureRestTestClient
class StartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MockMvcTester mockMvcTester;

    @Autowired
    private RestTestClient restTestClient;

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
