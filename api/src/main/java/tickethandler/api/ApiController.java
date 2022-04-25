package tickethandler.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ApiController {

    @GetMapping("/getEvents")
    public String getEvents() {
        log.info("API - getEvents");
        return "Hello Events!";
    }

    @GetMapping("/getEvent")
    public String getEvent(@RequestParam("eventId") Long eventId) {
        log.info(String.format("API - getEvent: %d", eventId));
        return String.format("Hello Event %d!", eventId);
    }
}
