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
import tickethandler.common.enums.ErrorType;

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

        EventListResponseDto eventListResponseDto;
        try {
            eventListResponseDto = restTemplate.getForObject(uri, EventListResponseDto.class);
        } catch (Exception e) {
            eventListResponseDto = new EventListResponseDto();
            eventListResponseDto.setSuccess(false);
            eventListResponseDto.setErrorType(ErrorType.API_TICKET_NOT_REACHABLE);
        }

        return eventListResponseDto;
    }

    @GetMapping("/getEvent")
    public EventResponseDto getEvent(@RequestParam("eventId") Long eventId) {
        log.info(String.format("API - getEvent: %d", eventId));
        String uri = ticketUrl + "/getEvent?eventId=" + eventId;
        RestTemplate restTemplate = new RestTemplate();

        EventResponseDto eventResponseDto;
        try {
            eventResponseDto = restTemplate.getForObject(uri, EventResponseDto.class);
        } catch (Exception e) {
            eventResponseDto = new EventResponseDto();
            eventResponseDto.setSuccess(false);
            eventResponseDto.setErrorType(ErrorType.API_TICKET_NOT_REACHABLE);
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
