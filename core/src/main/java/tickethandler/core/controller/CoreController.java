package tickethandler.core.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tickethandler.common.dto.BaseResponseDto;
import tickethandler.common.dto.pay.CardValidationResponseDto;
import tickethandler.common.dto.user.UsertokenResponseDto;
import tickethandler.common.enums.ErrorType;
import tickethandler.core.model.Userbankcard;
import tickethandler.core.model.Usertoken;
import tickethandler.core.service.UserTokenService;
import tickethandler.core.service.UserbankcardService;

import java.util.Objects;

@RestController
@Slf4j
public class CoreController {

    @Autowired
    UserbankcardService userbankcardService;

    @Autowired
    UserTokenService userTokenService;

    @GetMapping("/getCardValidation")
    public CardValidationResponseDto getCardValidation(
            @RequestParam("userId") Long userId,
            @RequestParam("cardId") Long cardId,
            @RequestParam("amount") Integer amount
    ) {
        log.info(String.format("CORE - getCardValidation: userId: %d, cardId: %d, amount: %d", userId, cardId, amount));

        CardValidationResponseDto cardValidationResponseDto = new CardValidationResponseDto();
        Userbankcard card = userbankcardService.getCardById(cardId);

        if(card == null || !Objects.equals(card.getUserId(), userId)) {
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

    @GetMapping("/getIsTokenValid")
    public UsertokenResponseDto getIsTokenValid(@RequestParam("token") String token) {
        log.info(String.format("CORE - getIsTokenValid: token: %s", token));
        UsertokenResponseDto usertokenResponseDto = new UsertokenResponseDto();

        if(token == "" || token == null) {
            usertokenResponseDto.setErrorType(ErrorType.CORE_USER_TOKEN_NULL);
            return usertokenResponseDto;
        }

        Usertoken usertoken = userTokenService.findByToken(token);


        if(usertoken != null && Objects.equals(token, usertoken.getToken())) {
            usertokenResponseDto.setErrorType(ErrorType.NO_ERROR);
            usertokenResponseDto.setUsertokenId(usertoken.getUsertokenId());
            usertokenResponseDto.setUserId(usertoken.getUserId());
            usertokenResponseDto.setToken(usertoken.getToken());
        } else {
            usertokenResponseDto.setErrorType(ErrorType.CORE_USER_TOKEN_INVALID);
        }

        return usertokenResponseDto;
    }

}
