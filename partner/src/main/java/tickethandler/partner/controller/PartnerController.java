package tickethandler.partner.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tickethandler.common.dto.event.EventDto;
import tickethandler.common.dto.event.EventResponseDto;
import tickethandler.common.dto.event.EventWithSeatListDto;
import tickethandler.common.dto.event.SeatDto;
import tickethandler.common.dto.event.SeatResponseDto;
import tickethandler.common.dto.pay.PayRequestDto;
import tickethandler.common.dto.pay.ReserveRequestDto;
import tickethandler.common.dto.pay.ReserveResponseDto;
import tickethandler.common.enums.ErrorType;
import tickethandler.partner.mapper.EventMapper;
import tickethandler.partner.mapper.SeatMapper;
import tickethandler.partner.model.Event;
import tickethandler.partner.model.Seat;
import tickethandler.partner.service.EventService;
import tickethandler.common.dto.event.EventListResponseDto;
import tickethandler.partner.service.SeatService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
public class PartnerController {

    @Autowired
    private EventMapper eventMapper;

    @Autowired
    private SeatMapper seatMapper;

    @Autowired
    private EventService eventService;

    @Autowired
    private SeatService seatService;

    @GetMapping("/getEvents")
    public EventListResponseDto getEvents() {
        log.info("PARTNER - getEvents");
        List<Event> eventList = eventService.getAllEvents();
        List<EventDto> eventDtoList = eventMapper.modelListToDtoList(eventList);

        EventListResponseDto eventListResponseDto = new EventListResponseDto();
        eventListResponseDto.setEventDtoList(eventDtoList);
        eventListResponseDto.setErrorType(ErrorType.NO_ERROR);

        return eventListResponseDto;
    }

    @GetMapping("/getEvent")
    public EventResponseDto getEvent(@RequestParam("eventId") Long eventId) {
        log.info(String.format("PARTNER - getEvent: %d", eventId));
        Event event = eventService.getEventById(eventId);

        EventResponseDto eventResponseDto = new EventResponseDto();
        if (event != null) {
            EventWithSeatListDto eventWithSeatListDto = eventMapper.modelToDtoWithSeatList(event);
            eventResponseDto.setEventWithSeatListDto(eventWithSeatListDto);
            eventResponseDto.setErrorType(ErrorType.NO_ERROR);
        } else {
            eventResponseDto.setEventWithSeatListDto(null);
            eventResponseDto.setErrorType(ErrorType.PARTNER_EVENT_NOT_FOUND);
        }

        return eventResponseDto;
    }

    @GetMapping("/getSeat")
    public SeatResponseDto getSeat(@RequestParam("seatId") Long seatId) {
        log.info(String.format("PARTNER - getSeat: %d", seatId));
        Seat seat = seatService.getSeatById(seatId);

        SeatResponseDto seatResponseDto = new SeatResponseDto();
        if (seat != null) {
            SeatDto seatDto = seatMapper.modelToDto(seat);
            seatResponseDto.setSeatDto(seatDto);
            seatResponseDto.setErrorType(ErrorType.NO_ERROR);
        } else {
            seatResponseDto.setSeatDto(null);
            seatResponseDto.setErrorType(ErrorType.PARTNER_SEAT_NOT_FOUND);
        }

        return seatResponseDto;
    }

    @PostMapping("/reserve")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ReserveResponseDto reserve(@RequestBody ReserveRequestDto reserveRequestDto) {
        log.info(String.format("PARTNER - reserve: EventId: %d, SeatId: %d", reserveRequestDto.getEventId(), reserveRequestDto.getSeatId()));
        Seat seat = seatService.getSeatById(reserveRequestDto.getSeatId());

        LocalDateTime now = LocalDateTime.now();
        ReserveResponseDto reserveResponseDto = new ReserveResponseDto(reserveRequestDto);
        if (seat != null) {
            if (seat.getEventId() == reserveRequestDto.getEventId()) {
                if(seat.getEvent().getStartTimestamp().isAfter(now)) {
                    if (!seat.getReserved()) {
                        seat.setReserved(true);
                        seatService.save(seat);

                        reserveResponseDto.setReservationId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
                        reserveResponseDto.setErrorType(ErrorType.NO_ERROR);
                    } else {
                        reserveResponseDto.setErrorType(ErrorType.PARTNER_SEAT_IS_SOLD);
                    }
                } else {
                    reserveResponseDto.setErrorType(ErrorType.PARTNER_EVENT_STARTED);
                }
            } else {
                reserveResponseDto.setErrorType(ErrorType.PARTNER_SEAT_IS_NOT_FOR_EVENT);
            }
        } else {
            reserveResponseDto = new ReserveResponseDto();
            reserveResponseDto.setErrorType(ErrorType.PARTNER_SEAT_NOT_FOUND);
        }

        return reserveResponseDto;
    }
}
