package tickethandler.partner.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tickethandler.common.dto.EventDto;
import tickethandler.partner.mapper.EventMapper;
import tickethandler.partner.model.Event;
import tickethandler.partner.service.EventService;
import tickethandler.common.dto.EventListResponseDto;

import java.util.List;

@RestController
@Slf4j
public class PartnerController {

    @Autowired
    private EventMapper eventMapper;

    @Autowired
    private EventService eventService;

    @GetMapping("/getEvents")
    public String getCardValidation() {
        log.info("PARTNER - getEvents - 123");
        List<Event> eventList = eventService.getAllEvents();
        List<EventDto> eventDtoList = eventMapper.modelListToDtoList(eventList);

        EventListResponseDto eventListResponseDto = new EventListResponseDto();
        eventListResponseDto.setEventDtoList(eventDtoList);

        return "Hello getEvents!";
    }
}
