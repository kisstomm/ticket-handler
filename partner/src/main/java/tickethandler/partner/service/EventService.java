package tickethandler.partner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tickethandler.partner.model.Event;
import tickethandler.partner.repository.EventRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();

        events = eventRepository.findAll();

        return events;

    }
}
