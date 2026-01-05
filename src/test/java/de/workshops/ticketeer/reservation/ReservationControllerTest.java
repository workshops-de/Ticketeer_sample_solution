package de.workshops.ticketeer.reservation;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

@WebMvcTest(ReservationController.class)
class ReservationControllerTest {

    @Autowired
    MockMvcTester mockMvcTester;

    @MockitoBean
    private ReservationService reservationService;

    @Test
    void testReserveTicketForAnEventIsHandledByReservationController() {
        mockMvcTester.perform(post("/api/reservations/event/2")
                .param("quantity", "3"));

        verify(reservationService).createReservation(new ReservationRequest(2L, 3, "Category 1"));
    }
}
