package tickethandler.partner.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tickethandler.common.dto.event.EventDto;
import tickethandler.common.dto.event.EventResponseDto;
import tickethandler.partner.mapper.EventMapper;
import tickethandler.partner.model.Event;
import tickethandler.partner.service.EventService;
import tickethandler.common.dto.event.EventListResponseDto;

import java.util.List;

@RestController
@Slf4j
public class PartnerController {

    @Autowired
    private EventMapper eventMapper;

    @Autowired
    private EventService eventService;

    @GetMapping("/getEvents")
    public EventListResponseDto getCardValidation() {
        log.info("PARTNER - getEvents");
        List<Event> eventList = eventService.getAllEvents();
        List<EventDto> eventDtoList = eventMapper.modelListToDtoList(eventList);

        EventListResponseDto eventListResponseDto = new EventListResponseDto();
        eventListResponseDto.setEventDtoList(eventDtoList);

        return eventListResponseDto;
    }

    @GetMapping("/getEvent")
    public EventResponseDto getEvent(@RequestParam("eventId") Long eventId) {
        log.info(String.format("PARTNER - getEvent: %d", eventId));
        Event event = eventService.getEventById(eventId);
        EventDto eventDto = eventMapper.modelToDto(event);

        EventResponseDto eventResponseDto = new EventResponseDto();
        eventResponseDto.setEventDto(eventDto);

        return eventResponseDto;
    }
}
