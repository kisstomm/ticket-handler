package tickethandler.common.dto.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeatDto {

    private Long seatId;
    private Integer price;
    private String currency;
    private Boolean reserved;
    private Long eventId;
}
