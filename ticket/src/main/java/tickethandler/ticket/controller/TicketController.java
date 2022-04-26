package tickethandler.ticket.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import tickethandler.common.dto.event.EventListResponseDto;
import tickethandler.common.dto.event.EventResponseDto;
import tickethandler.common.enums.ErrorType;

@RestController
@Slf4j
public class TicketController {

    @Value("${tickethandler.partner.url}")
    private String partnerUrl;

    @GetMapping("/getEvents")
    public EventListResponseDto getEvents() {
        log.info("TICKET - getEvents");
        String uri = partnerUrl +  "/getEvents";
        RestTemplate restTemplate = new RestTemplate();
        EventListResponseDto eventListResponseDto = restTemplate.getForObject(uri, EventListResponseDto.class);
        return eventListResponseDto;
    }

    @GetMapping("/getEvent")
    public EventResponseDto getEvent(@RequestParam("eventId") Long eventId) {
        log.info(String.format("TICKET - getEvent: %d", eventId));
        String uri = partnerUrl + "/getEvent?eventId=" + eventId;
        RestTemplate restTemplate = new RestTemplate();
        EventResponseDto eventResponseDto = restTemplate.getForObject(uri, EventResponseDto.class);

        if(!eventResponseDto.isSuccess()) {
            switch (eventResponseDto.getErrorType()) {
                case PARTNER_EVENT_NOT_FOUND:
                    eventResponseDto.setErrorType(ErrorType.TICKET_EVENT_NOT_FOUND);
                break;
            }
        }

        return eventResponseDto;
    }

}
