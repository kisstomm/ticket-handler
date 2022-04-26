package tickethandler.common.dto.pay;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class PayResponseDto extends ReserveResponseDto {
    private Long cardId;
}
