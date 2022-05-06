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

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles({"test"})
@RunWith(SpringRunner.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CoreControllerTest {
    @Autowired
    CoreController coreController;

    @Autowired
    UsertokenRepository usertokenRepository;

    @BeforeAll
    protected void setUp() {
        Usertoken usertoken = new Usertoken();
        usertoken.setUsertokenId(1L);
        usertoken.setUserId(123L);
        usertoken.setToken("T0k3n");

        usertokenRepository.save(usertoken);
    }

    @Test
    void testIsTokenValid() {
        final String tokenValid = "T0k3n";
        final String tokenInValid = "invalid token";
        final String tokenEmpty = "";
        final String tokenNull = null;

        UsertokenResponseDto usertokenResponseDto = coreController.getIsTokenValid(tokenValid);
        assertTrue(usertokenResponseDto.isSuccess());
        assertEquals(usertokenResponseDto.getErrorType(), ErrorType.NO_ERROR);

        usertokenResponseDto = coreController.getIsTokenValid(tokenInValid);
        assertFalse(usertokenResponseDto.isSuccess());
        assertEquals(usertokenResponseDto.getErrorType(), ErrorType.CORE_USER_TOKEN_INVALID);

        usertokenResponseDto = coreController.getIsTokenValid(tokenEmpty);
        assertFalse(usertokenResponseDto.isSuccess());
        assertEquals(usertokenResponseDto.getErrorType(), ErrorType.CORE_USER_TOKEN_NULL);
    }
}
