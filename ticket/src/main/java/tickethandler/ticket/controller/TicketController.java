package tickethandler.ticket.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import tickethandler.common.dto.event.EventDto;
import tickethandler.common.dto.event.EventListResponseDto;
import tickethandler.common.dto.event.EventResponseDto;

@RestController
@Slf4j
public class TicketController {
    @GetMapping("/getEvents")
    public EventListResponseDto getEvents() {
        log.info("TICKET - getEvents");
        String uri = "http://partner:8083/getEvents";
        RestTemplate restTemplate = new RestTemplate();
        EventListResponseDto eventListResponseDto = restTemplate.getForObject(uri, EventListResponseDto.class);
        return eventListResponseDto;
    }

    @GetMapping("/getEvent")
    public EventResponseDto getEvent(@RequestParam("eventId") Long eventId) {
        log.info(String.format("TICKET - getEvent: %d", eventId));
        String uri = "http://partner:8083/getEvent?eventId=" + eventId;
        RestTemplate restTemplate = new RestTemplate();
        EventResponseDto eventResponseDto = restTemplate.getForObject(uri, EventResponseDto.class);

        return eventResponseDto;
    }

}
