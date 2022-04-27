package tickethandler.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tickethandler.core.model.Usertoken;
import tickethandler.core.repository.UsertokenRepository;

@Service
public class UserTokenService {

    @Autowired
    UsertokenRepository usertokenRepository;

    public Usertoken findByToken(String token) {
        return usertokenRepository.findByToken(token);
    }
}
