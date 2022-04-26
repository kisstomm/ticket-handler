package tickethandler.common.dto.pay;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class ReserveRequestDto {
    public Long eventId;
    public Long seatId;
}
