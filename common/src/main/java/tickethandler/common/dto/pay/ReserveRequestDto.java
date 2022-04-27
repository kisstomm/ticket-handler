package tickethandler.common.dto.pay;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class ReserveRequestDto {
    private Long eventId;
    private Long seatId;
}
