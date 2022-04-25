package tickethandler.ticket.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TicketController {
    @GetMapping("/getEvents")
    public String getEvents() {
        log.info("TICKET - getEvents");
        return "Hello Events!";
    }

}
