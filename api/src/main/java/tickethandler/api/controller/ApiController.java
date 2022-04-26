package tickethandler.api.controller;

import lombok.extern.slf4j.Slf4j;
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

@RestController
@Slf4j
public class ApiController {

    @GetMapping("/getEvents")
    public String getEvents() {
        log.info("API - getEvents");
        String uri = "http://ticket:8081/getEvents";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        return result;
    }

    @GetMapping("/getEvent")
    public String getEvent(@RequestParam("eventId") Long eventId) {
        log.info(String.format("API - getEvent: %d", eventId));
        return String.format("Hello Event %d!", eventId);
    }

    @PostMapping("/pay")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String pay(@RequestBody PayDto payDto) {
        log.info(String.format("API - pay: EventId: %d, SeatId: %d, CardId: %d", payDto.getEventId(), payDto.getSeatId(), payDto.CardId));
        return String.format("Hello pay! EventId: %d, SeatId: %d, CardId: %d", payDto.getEventId(), payDto.getSeatId(), payDto.CardId);
    }
}
