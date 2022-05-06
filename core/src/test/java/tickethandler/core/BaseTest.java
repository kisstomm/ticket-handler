package tickethandler.core;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import tickethandler.core.model.Userbankcard;
import tickethandler.core.model.Usertoken;
import tickethandler.core.repository.UserbankcardRepository;
import tickethandler.core.repository.UsertokenRepository;
import tickethandler.core.util.CoreTestConstants;

@ActiveProfiles({"test"})
@RunWith(SpringRunner.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest implements CoreTestConstants {

    @Autowired
    UsertokenRepository usertokenRepository;

    @Autowired
    UserbankcardRepository userbankcardRepository;


    @BeforeAll
    protected void setUp() {
        Usertoken usertoken = new Usertoken();
        usertoken.setUsertokenId(USER_TOKEN_ID_1);
        usertoken.setUserId(USER_ID_1);
        usertoken.setToken(TOKEN_1);
        usertokenRepository.save(usertoken);

        Userbankcard userbankcard = new Userbankcard();
        userbankcard.setUserbankcardId(USER_BANK_CARD_ID_1);
        userbankcard.setUserId(USER_ID_1);
        userbankcard.setCardId(CARD_ID_1);
        userbankcard.setCardNumber(CARD_NUMBER_1);
        userbankcard.setCvc(CVC_1);
        userbankcard.setName(CARD_NAME_1);
        userbankcard.setAmount(AMOUNT_1);
        userbankcard.setCurrency(CURRENCY_1);
        userbankcardRepository.save(userbankcard);
    }
}
