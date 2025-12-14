package de.workshops.ticketeer;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@WebMvcTest(EventController.class)
@Import(EventRepository.class)
@AutoConfigureMockMvc
public class EventControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  @Test
  void getEvents() throws Exception {
    var eventsResult = mockMvc
        .perform(get("/api/events"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(3)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", is("Rock Concert")))
        .andReturn();

    String jsonPayload = eventsResult.getResponse().getContentAsString();

    List<EventDto> events = objectMapper.readValue(jsonPayload, new TypeReference<>(){});
    assertEquals(3, events.size());
    assertEquals("Rock Concert", events.getFirst().name());
  }

  @Test
  void getEvent() throws Exception {
    var eventResult = mockMvc
        .perform(get("/api/events/2"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is("Jazz Festival")))
        .andReturn();

    String jsonPayload = eventResult.getResponse().getContentAsString();

    EventDto event = objectMapper.readValue(jsonPayload, EventDto.class);
    assertEquals("Jazz Festival", event.name());
  }
}
