package tickethandler.core.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import tickethandler.common.dto.user.UsertokenResponseDto;
import tickethandler.common.enums.ErrorType;
import tickethandler.core.model.Usertoken;
import tickethandler.core.repository.UsertokenRepository;
import tickethandler.core.util.CoreTestConstants;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles({"test"})
@RunWith(SpringRunner.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CoreControllerTest implements CoreTestConstants {
    @Autowired
    CoreController coreController;

    @Autowired
    UsertokenRepository usertokenRepository;



    @BeforeAll
    protected void setUp() {
        Usertoken usertoken = new Usertoken();
        usertoken.setUsertokenId(USER_TOKEN_ID_1);
        usertoken.setUserId(USER_ID_1);
        usertoken.setToken(TOKEN_1);

        usertokenRepository.save(usertoken);
    }

    @Test
    void testIsTokenValid() {
        final String tokenEmpty = "";
        final String tokenNull = null;

        assertUsertokenResponseDto(TOKEN_1, ErrorType.NO_ERROR);
        assertUsertokenResponseDto(TOKEN_INVALID, ErrorType.CORE_USER_TOKEN_INVALID);
        assertUsertokenResponseDto(tokenEmpty, ErrorType.CORE_USER_TOKEN_NULL);
        assertUsertokenResponseDto(tokenNull, ErrorType.CORE_USER_TOKEN_NULL);
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
