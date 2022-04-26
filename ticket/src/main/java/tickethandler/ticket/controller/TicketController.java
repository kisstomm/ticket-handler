package tickethandler.ticket.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class TicketController {
    @GetMapping("/getEvents")
    public String getEvents() {
        log.info("TICKET - getEvents");
        String uri = "http://partner:8083/getEvents";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        return result;
    }

}
