package tickethandler.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tickethandler.core.model.Userbankcard;
import tickethandler.core.repository.UserbankcardRepository;

@Service
public class UserbankcardService {

    @Autowired
    UserbankcardRepository userbankcardRepository;

    public Userbankcard getCardById(Long cardId) {
        return userbankcardRepository.findById(cardId).orElse(null);
    }
}
