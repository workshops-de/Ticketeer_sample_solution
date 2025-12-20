package de.workshops.ticketeer.event;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@WebMvcTest(EventController.class)
@AutoConfigureMockMvc
class EventControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockitoBean
  private EventRepository eventRepository;

  @Test
  void getEvents() throws Exception {
    when(eventRepository.findAll()).thenReturn(mockEvents());

    var eventsResult = mockMvc
        .perform(get("/api/events"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(3)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", is("Rock Concert")))
        .andReturn();

    String jsonPayload = eventsResult.getResponse().getContentAsString();

    List<EventDto> events = objectMapper.readValue(jsonPayload, new TypeReference<>() {
    });
    assertEquals(3, events.size());
    assertEquals("Rock Concert", events.getFirst().name());
  }

  @Test
  void getEvent() throws Exception {
    when(eventRepository.findById(2L)).thenReturn(
        mockEvents().stream().filter(e -> e.getId().equals(2L)).findFirst()
    );

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

  @Test
  void getNonExistingEvent() throws Exception {
    mockMvc
        .perform(get("/api/events/5"))
        .andDo(print())
        .andExpect(status().isNotFound())
        .andReturn();
  }

  private List<Event> mockEvents() {
    return List.of(
        Event.builder().id(1L).name("Rock Concert").venue("Stadium A").build(),
        Event.builder().id(2L).name("Jazz Festival").venue("Park B").build(),
        Event.builder().id(3L).name("Classical Night").venue("Concert Hall C").build()
    );
  }
}
