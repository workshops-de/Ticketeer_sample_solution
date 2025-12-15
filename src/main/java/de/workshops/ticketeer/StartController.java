package de.workshops.ticketeer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ping")
class StartController {

    @GetMapping
    String ping() {
        return """
            {"status": "ticketeer-ok"}""";
    }
}
