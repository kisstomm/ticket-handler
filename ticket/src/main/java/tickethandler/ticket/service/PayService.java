package tickethandler.ticket.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tickethandler.common.dto.pay.CardValidationResponseDto;
import tickethandler.common.dto.pay.PayRequestDto;
import tickethandler.common.dto.pay.PayResponseDto;
import tickethandler.common.dto.pay.ReserveRequestDto;
import tickethandler.common.dto.pay.ReserveResponseDto;
import tickethandler.common.enums.ErrorType;

@Service
@Slf4j
public class PayService {

    @Value("${tickethandler.core.url}")
    private String coreUrl;

    @Value("${tickethandler.partner.url}")
    private String partnerUrl;

    public CardValidationResponseDto checkBankCard(PayRequestDto payRequestDto, Integer amount) {
        log.info(String.format("TICKET - checkBankCard: EventId: %d, SeatId: %d, CardId: %d, amount: %d", payRequestDto.getEventId(), payRequestDto.getSeatId(), payRequestDto.getCardId(), amount));
        String uri = coreUrl + String.format("/getCardValidation?cardId=%d&amount=%d", payRequestDto.getCardId(), amount);
        RestTemplate restTemplate = new RestTemplate();

        CardValidationResponseDto cardValidationResponseDto;
        try {
            cardValidationResponseDto = restTemplate.getForObject(uri, CardValidationResponseDto.class);
        } catch(Exception e) {
            log.info("TICKET error: " + e.getMessage());
            cardValidationResponseDto = new CardValidationResponseDto();
            cardValidationResponseDto.setErrorType(ErrorType.TICKET_PARTNER_NOT_REACHABLE);
        }

        return cardValidationResponseDto;

    }

    public PayResponseDto reserveTicket(PayRequestDto payRequestDto) {
        String uri = partnerUrl + "/reserve";
        RestTemplate restTemplate = new RestTemplate();

        ReserveRequestDto reserveRequestDto = payRequestDto;
        ReserveResponseDto reserveResponseDto;
        try {
            reserveResponseDto = restTemplate.postForObject(uri, reserveRequestDto, ReserveResponseDto.class);
        } catch(Exception e) {
            log.info("TICKET error: " + e.getMessage());
            reserveResponseDto = new ReserveResponseDto();
            reserveResponseDto.setErrorType(ErrorType.TICKET_PARTNER_NOT_REACHABLE);
        }

        PayResponseDto payResponseDto = new PayResponseDto();
        payResponseDto.setEventId(reserveRequestDto.getEventId());
        payResponseDto.setSeatId(reserveRequestDto.getSeatId());
        payResponseDto.setCardId(payRequestDto.getCardId());
        payResponseDto.setReservationId(reserveResponseDto.getReservationId());
        payResponseDto.setErrorType(reserveResponseDto.getErrorType());

        if(!payResponseDto.isSuccess()) {
            switch (payResponseDto.getErrorType()) {
                case PARTNER_SEAT_IS_SOLD:
                    payResponseDto.setErrorType(ErrorType.TICKET_SEAT_IS_SOLD);
                    break;
                case PARTNER_EVENT_STARTED:
                    payResponseDto.setErrorType(ErrorType.TICKET_EVENT_STARTED);
                    break;
                case PARTNER_SEAT_IS_NOT_FOR_EVENT:
                    payResponseDto.setErrorType(ErrorType.TICKET_SEAT_IS_NOT_FOR_EVENT);
                    break;
                case PARTNER_SEAT_NOT_FOUND:
                    payResponseDto.setErrorType(ErrorType.TICKET_SEAT_NOT_FOUND);
                    break;
            }
        }


        return payResponseDto;
    }
}
