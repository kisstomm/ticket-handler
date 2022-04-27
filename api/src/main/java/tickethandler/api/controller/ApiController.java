package tickethandler.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import tickethandler.api.service.TokenService;
import tickethandler.common.dto.BaseResponseDto;
import tickethandler.common.dto.pay.PayRequestDto;
import tickethandler.common.dto.event.EventListResponseDto;
import tickethandler.common.dto.event.EventResponseDto;
import tickethandler.common.dto.pay.PayResponseDto;
import tickethandler.common.dto.user.UsertokenResponseDto;
import tickethandler.common.enums.ErrorType;

@RestController
@Slf4j
public class ApiController {
    @Value("${tickethandler.ticket.url}")
    private String ticketUrl;

    @Autowired
    TokenService tokenService;

    @GetMapping("/getEvents")
    public EventListResponseDto getEvents(@RequestHeader(name = "User-Token", defaultValue = "") String token) {
        log.info("API - getEvents: " + token);
        EventListResponseDto eventListResponseDto;

        UsertokenResponseDto usertokenResponseDto = tokenService.isTokenValid(token);
        if (!usertokenResponseDto.isSuccess()) {
            eventListResponseDto = new EventListResponseDto();
            eventListResponseDto.setErrorType(usertokenResponseDto.getErrorType());

            return eventListResponseDto;
        }


        RestTemplate restTemplate = new RestTemplate();
        String uri = ticketUrl + "/getEvents";
        try {
            eventListResponseDto = restTemplate.getForObject(uri, EventListResponseDto.class);
        } catch (Exception e) {
            log.error("API error: " + e.getMessage());
            eventListResponseDto = new EventListResponseDto();
            eventListResponseDto.setErrorType(ErrorType.API_TICKET_NOT_REACHABLE);
        }

        return eventListResponseDto;
    }

    @GetMapping("/getEvent")
    public EventResponseDto getEvent(
            @RequestHeader(name = "User-Token", defaultValue = "") String token,
            @RequestParam("eventId") Long eventId
        ) {
        log.info(String.format("API - getEvent: %d", eventId));
        String uri = ticketUrl + "/getEvent?eventId=" + eventId;
        RestTemplate restTemplate = new RestTemplate();
        EventResponseDto eventResponseDto;

        UsertokenResponseDto usertokenResponseDto = tokenService.isTokenValid(token);
        if (!usertokenResponseDto.isSuccess()) {
            eventResponseDto = new EventResponseDto();
            eventResponseDto.setErrorType(usertokenResponseDto.getErrorType());

            return eventResponseDto;
        }

        try {
            eventResponseDto = restTemplate.getForObject(uri, EventResponseDto.class);
        } catch (Exception e) {
            log.error("API error: " + e.getMessage());
            eventResponseDto = new EventResponseDto();
            eventResponseDto.setErrorType(ErrorType.API_TICKET_NOT_REACHABLE);
        }

        return eventResponseDto;
    }

    @PostMapping("/pay")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public PayResponseDto pay(
            @RequestHeader(name = "User-Token", defaultValue = "") String token,
            @RequestBody PayRequestDto payRequestDto
    ) {
        log.info(String.format("API - pay: EventId: %d, SeatId: %d, CardId: %d", payRequestDto.getEventId(), payRequestDto.getSeatId(), payRequestDto.getCardId()));
        RestTemplate restTemplate = new RestTemplate();
        PayResponseDto payResponseDto;

        UsertokenResponseDto usertokenResponseDto = tokenService.isTokenValid(token);
        if (!usertokenResponseDto.isSuccess()) {
            payResponseDto = new PayResponseDto();
            payResponseDto.setErrorType(usertokenResponseDto.getErrorType());

            return payResponseDto;
        }

        String uri = ticketUrl + "/pay";
        payRequestDto.setUserId(usertokenResponseDto.getUserId());
        payResponseDto = restTemplate.postForObject(uri, payRequestDto, PayResponseDto.class);

        return payResponseDto;
    }
}
