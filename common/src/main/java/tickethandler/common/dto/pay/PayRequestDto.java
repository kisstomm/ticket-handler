package tickethandler.common.dto.pay;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Setter
@Getter
@NoArgsConstructor
public class PayRequestDto extends ReserveRequestDto {
    public Long cardId;
}
