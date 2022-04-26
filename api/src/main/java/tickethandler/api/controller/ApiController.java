package tickethandler.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import tickethandler.api.dto.PayDto;
import tickethandler.common.dto.event.EventListResponseDto;
import tickethandler.common.dto.event.EventResponseDto;

@RestController
@Slf4j
public class ApiController {
    @Value("${tickethandler.ticket.url}")
    private String ticketUrl;

    @GetMapping("/getEvents")
    public EventListResponseDto getEvents() {
        log.info("API - getEvents");
        String uri = ticketUrl + "/getEvents";
        RestTemplate restTemplate = new RestTemplate();
        EventListResponseDto eventListResponseDto = restTemplate.getForObject(uri, EventListResponseDto.class);
        return eventListResponseDto;
    }

    @GetMapping("/getEvent")
    public EventResponseDto getEvent(@RequestParam("eventId") Long eventId) {
        log.info(String.format("API - getEvent: %d", eventId));
        String uri = ticketUrl + "/getEvent?eventId=" + eventId;
        RestTemplate restTemplate = new RestTemplate();
        EventResponseDto eventResponseDto = restTemplate.getForObject(uri, EventResponseDto.class);

        if(!eventResponseDto.isSuccess()) {
            switch (eventResponseDto.getErrorCode()) {
                case 90001:
                    eventResponseDto.setErrorCode(90001);
                    eventResponseDto.setErrorMessage("Nem létezik ilyen esemény!");
                    break;
            }
        }

        return eventResponseDto;
    }

    @PostMapping("/pay")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String pay(@RequestBody PayDto payDto) {
        log.info(String.format("API - pay: EventId: %d, SeatId: %d, CardId: %d", payDto.getEventId(), payDto.getSeatId(), payDto.CardId));
        return String.format("Hello pay! EventId: %d, SeatId: %d, CardId: %d", payDto.getEventId(), payDto.getSeatId(), payDto.CardId);
    }
}
