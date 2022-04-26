package tickethandler.common.dto.pay;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Setter
@Getter
@NoArgsConstructor
public class PayRequestDto {
    public Long eventId;
    public Long SeatId;
    public Long CardId;
}
