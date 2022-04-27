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
import tickethandler.common.dto.event.SeatResponseDto;
import tickethandler.common.dto.pay.CardValidationResponseDto;
import tickethandler.common.dto.pay.PayRequestDto;
import tickethandler.common.dto.pay.PayResponseDto;
import tickethandler.common.enums.ErrorType;
import tickethandler.ticket.service.EventService;
import tickethandler.ticket.service.PayService;

@RestController
@Slf4j
public class TicketController {



    @Autowired
    private EventService eventService;

    @Autowired
    private PayService payService;

    @GetMapping("/getEvents")
    public EventListResponseDto getEvents() {
        log.info("TICKET - getEvents");
        EventListResponseDto eventListResponseDto = eventService.getEvents();

        return eventListResponseDto;
    }

    @GetMapping("/getEvent")
    public EventResponseDto getEvent(@RequestParam("eventId") Long eventId) {
        log.error(String.format("TICKET - getEvent: %d", eventId));
        EventResponseDto eventResponseDto = eventService.getEvent(eventId);

        return eventResponseDto;
    }


    @PostMapping("/pay")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public PayResponseDto pay(@RequestBody PayRequestDto payRequestDto) {
        log.info(String.format("TICKET - pay: EventId: %d, SeatId: %d, CardId: %d", payRequestDto.getEventId(), payRequestDto.getSeatId(), payRequestDto.getCardId()));
        PayResponseDto payResponseDto;

        SeatResponseDto seatResponseDto = eventService.getSeat(payRequestDto.getSeatId());
        if (!seatResponseDto.isSuccess()) {
            payResponseDto = new PayResponseDto();
            payResponseDto.setErrorType(ErrorType.TICKET_SEAT_NOT_FOUND);

            return payResponseDto;
        }

        CardValidationResponseDto cardValidationResponseDto = payService.checkBankCard(payRequestDto, seatResponseDto.getSeatDto().getPrice());
        if (!cardValidationResponseDto.isSuccess()) {
            payResponseDto = new PayResponseDto();
            payResponseDto.setErrorType(cardValidationResponseDto.getErrorType());

            return payResponseDto;
        }

        payResponseDto = payService.reserveTicket(payRequestDto);

        return payResponseDto;
    }

}
