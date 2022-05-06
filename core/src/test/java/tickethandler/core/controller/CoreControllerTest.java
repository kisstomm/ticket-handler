package tickethandler.core.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tickethandler.common.dto.pay.CardValidationResponseDto;
import tickethandler.common.dto.user.UsertokenResponseDto;
import tickethandler.common.enums.ErrorType;
import tickethandler.core.BaseTest;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CoreControllerTest extends BaseTest {

    @Autowired
    CoreController coreController;

    @Test
    void testIsTokenValid() {
        final String tokenEmpty = "";
        final String tokenNull = null;

        assertUsertokenResponseDto(TOKEN_1, ErrorType.NO_ERROR);
        assertUsertokenResponseDto(TOKEN_INVALID, ErrorType.CORE_USER_TOKEN_INVALID);
        assertUsertokenResponseDto(tokenEmpty, ErrorType.CORE_USER_TOKEN_NULL);
        assertUsertokenResponseDto(tokenNull, ErrorType.CORE_USER_TOKEN_NULL);
    }

    @Test
    void testCardValidation() {
        CardValidationResponseDto cardValidationResponseDto;

        cardValidationResponseDto = coreController.getCardValidation(USER_ID_1, USER_BANK_CARD_ID_1, AMOUNT_1);
        assertTrue(cardValidationResponseDto.isSuccess());
        assertEquals(cardValidationResponseDto.getErrorType(), ErrorType.NO_ERROR);

        cardValidationResponseDto = coreController.getCardValidation(USER_ID_1, USER_BANK_CARD_ID_1, 0);
        assertTrue(cardValidationResponseDto.isSuccess());
        assertEquals(cardValidationResponseDto.getErrorType(), ErrorType.NO_ERROR);

        cardValidationResponseDto = coreController.getCardValidation(USER_ID_1, USER_BANK_CARD_ID_1, AMOUNT_1 - 1);
        assertTrue(cardValidationResponseDto.isSuccess());
        assertEquals(cardValidationResponseDto.getErrorType(), ErrorType.NO_ERROR);

        cardValidationResponseDto = coreController.getCardValidation(USER_ID_1, USER_BANK_CARD_ID_1, AMOUNT_1 + 1);
        assertFalse(cardValidationResponseDto.isSuccess());
        assertEquals(cardValidationResponseDto.getErrorType(), ErrorType.CORE_NOT_ENOUGH_MONEY);



        cardValidationResponseDto = coreController.getCardValidation(USER_ID_1, USER_BANK_CARD_ID_2, 0);
        assertFalse(cardValidationResponseDto.isSuccess());
        assertEquals(cardValidationResponseDto.getErrorType(), ErrorType.CORE_CARD_NOT_FOR_USER);

        cardValidationResponseDto = coreController.getCardValidation(USER_ID_2, USER_BANK_CARD_ID_1, 0);
        assertFalse(cardValidationResponseDto.isSuccess());
        assertEquals(cardValidationResponseDto.getErrorType(), ErrorType.CORE_CARD_NOT_FOR_USER);
    }

    void assertUsertokenResponseDto(String token, ErrorType errorType) {
        UsertokenResponseDto usertokenResponseDto = coreController.getIsTokenValid(token);

        if (errorType == ErrorType.NO_ERROR) {
            assertTrue(usertokenResponseDto.isSuccess());
            assertNotNull(usertokenResponseDto.getUsertokenId());
            assertNotNull(usertokenResponseDto.getUserId());
            assertEquals(usertokenResponseDto.getToken(), token);
        } else {
            assertFalse(usertokenResponseDto.isSuccess());
            assertNull(usertokenResponseDto.getUsertokenId());
            assertNull(usertokenResponseDto.getUserId());
            assertNull(usertokenResponseDto.getToken());
        }

        assertEquals(usertokenResponseDto.getErrorType(), errorType);
    }
}
