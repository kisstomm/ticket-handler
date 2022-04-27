package tickethandler.common.dto.pay;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tickethandler.common.dto.BaseResponseDto;


@Setter
@Getter
@NoArgsConstructor
public class ReserveResponseDto extends BaseResponseDto {
    private Long eventId;
    private Long seatId;
    private Long reservationId;

    public ReserveResponseDto(ReserveRequestDto reserveRequestDto) {
        this.eventId = reserveRequestDto.getEventId();
        this.seatId = reserveRequestDto.getSeatId();
    }
}
