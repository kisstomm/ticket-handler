package tickethandler.common.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tickethandler.common.dto.BaseResponseDto;

@Getter
@Setter
@NoArgsConstructor
public class UsertokenResponseDto extends BaseResponseDto {
    private Long usertokenId;
    private Long userId;
    private String token;
}
