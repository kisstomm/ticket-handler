package tickethandler.ticket.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
import tickethandler.common.dto.event.EventListResponseDto;
import tickethandler.common.dto.event.EventResponseDto;
import tickethandler.common.dto.pay.PayRequestDto;
import tickethandler.common.dto.pay.PayResponseDto;
import tickethandler.common.dto.pay.ReserveRequestDto;
import tickethandler.common.dto.pay.ReserveResponseDto;
import tickethandler.common.enums.ErrorType;
import tickethandler.ticket.service.PayService;

@RestController
@Slf4j
public class TicketController {

    @Value("${tickethandler.partner.url}")
    private String partnerUrl;

    @Autowired
    private PayService payService;

    @GetMapping("/getEvents")
    public EventListResponseDto getEvents() {
        log.info("TICKET - getEvents");
        String uri = partnerUrl +  "/getEvents";
        RestTemplate restTemplate = new RestTemplate();

        EventListResponseDto eventListResponseDto;
        try {
            eventListResponseDto = restTemplate.getForObject(uri, EventListResponseDto.class);
        } catch(Exception e) {
            log.error("TICKET error: " + e.getMessage());
            eventListResponseDto = new EventListResponseDto();
            eventListResponseDto.setErrorType(ErrorType.TICKET_PARTNER_NOT_REACHABLE);
        }
        return eventListResponseDto;
    }

    @GetMapping("/getEvent")
    public EventResponseDto getEvent(@RequestParam("eventId") Long eventId) {
        log.error(String.format("TICKET - getEvent: %d", eventId));
        String uri = partnerUrl + "/getEvent?eventId=" + eventId;
        RestTemplate restTemplate = new RestTemplate();

        EventResponseDto eventResponseDto;
        try {
             eventResponseDto = restTemplate.getForObject(uri, EventResponseDto.class);
        } catch(Exception e) {
            log.info("TICKET error: " + e.getMessage());
            eventResponseDto = new EventResponseDto();
            eventResponseDto.setErrorType(ErrorType.TICKET_PARTNER_NOT_REACHABLE);
        }

        if(!eventResponseDto.isSuccess()) {
            switch (eventResponseDto.getErrorType()) {
                case PARTNER_EVENT_NOT_FOUND:
                    eventResponseDto.setErrorType(ErrorType.TICKET_EVENT_NOT_FOUND);
                break;
            }
        }

        return eventResponseDto;
    }


    @PostMapping("/pay")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public PayResponseDto pay(@RequestBody PayRequestDto payRequestDto) {
        log.info(String.format("TICKET - pay: EventId: %d, SeatId: %d, CardId: %d", payRequestDto.getEventId(), payRequestDto.getSeatId(), payRequestDto.getCardId()));

        PayResponseDto payResponseDto = payService.reserveTicket(payRequestDto, partnerUrl + "/reserve");

        return payResponseDto;
    }

}
