package tickethandler.api.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tickethandler.common.dto.BaseResponseDto;
import tickethandler.common.dto.event.EventListResponseDto;
import tickethandler.common.dto.user.UsertokenResponseDto;
import tickethandler.common.enums.ErrorType;

@Service
@Slf4j
public class TokenService{

    @Value("${tickethandler.core.url}")
    private String coreUrl;

    public UsertokenResponseDto isTokenValid(String token) {
        log.info("API - TokenService - isTokenValid: " + token);
        String uri = coreUrl + "/getIsTokenValid?token=" + token;
        RestTemplate restTemplate = new RestTemplate();

        UsertokenResponseDto usertokenResponseDto;
        try {
            usertokenResponseDto = restTemplate.getForObject(uri, UsertokenResponseDto.class);
        } catch (Exception e) {
            log.error("API error: " + e.getMessage());
            usertokenResponseDto = new UsertokenResponseDto();
            usertokenResponseDto.setErrorType(ErrorType.API_CORE_NOT_REACHABLE);
        }

        return usertokenResponseDto;
    }
}
