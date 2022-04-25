package tickethandler.api.dto;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Setter
@Getter
@NoArgsConstructor
public class PayDto {
    public Long eventId;
    public Long SeatId;
    public Long CardId;
}
