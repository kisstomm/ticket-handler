package tickethandler.ticket.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tickethandler.common.dto.event.EventListResponseDto;
import tickethandler.common.dto.event.EventResponseDto;
import tickethandler.common.dto.event.SeatResponseDto;
import tickethandler.common.enums.ErrorType;

@Service
@Slf4j
public class EventService {

    @Value("${tickethandler.partner.url}")
    private String partnerUrl;

    public EventListResponseDto getEvents(){
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

    public EventResponseDto getEvent(Long eventId) {
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

    public SeatResponseDto getSeat(Long seatId) {
        String uri = partnerUrl + "/getSeat?seatId=" + seatId;
        RestTemplate restTemplate = new RestTemplate();

        SeatResponseDto seatResponseDto;
        try {
            seatResponseDto = restTemplate.getForObject(uri, SeatResponseDto.class);
        } catch(Exception e) {
            log.info("TICKET error: " + e.getMessage());
            seatResponseDto = new SeatResponseDto();
            seatResponseDto.setErrorType(ErrorType.TICKET_PARTNER_NOT_REACHABLE);
        }

        if(!seatResponseDto.isSuccess()) {
            switch (seatResponseDto.getErrorType()) {
                case PARTNER_SEAT_NOT_FOUND:
                    seatResponseDto.setErrorType(ErrorType.TICKET_SEAT_NOT_FOUND);
                    break;
            }
        }

        return seatResponseDto;
    }
}
