package tickethandler.core.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tickethandler.common.dto.pay.CardValidationResponseDto;
import tickethandler.common.enums.ErrorType;
import tickethandler.core.model.Userbankcard;
import tickethandler.core.service.UserbankcardService;

@RestController
@Slf4j
public class CoreController {

    @Autowired
    UserbankcardService userbankcardService;

    @GetMapping("/getCardValidation")
    public CardValidationResponseDto getCardValidation(@RequestParam("cardId") Long cardId, @RequestParam("amount") Integer amount) {
        log.info(String.format("CORE - getCardValidation: cardId: %d, amount: %d", cardId, amount));

        CardValidationResponseDto cardValidationResponseDto = new CardValidationResponseDto();
        Userbankcard card = userbankcardService.getCardById(cardId);

        if(card == null) {
            cardValidationResponseDto.setErrorType(ErrorType.CORE_CARD_NOT_FOR_USER);
            return cardValidationResponseDto;
        }

        if (card.getAmount() < amount) {
            cardValidationResponseDto.setErrorType(ErrorType.CORE_NOT_ENOUGH_MONEY);
            return cardValidationResponseDto;
        }


        cardValidationResponseDto.setErrorType(ErrorType.NO_ERROR);

        return cardValidationResponseDto;
    }

}
