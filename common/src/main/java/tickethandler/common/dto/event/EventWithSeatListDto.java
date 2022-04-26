package tickethandler.common.dto.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventWithSeatListDto {

    private Long eventId;
    private String title;
    private String location;
    private LocalDateTime startTimestamp;
    private LocalDateTime endTimestamp;
    private List<SeatDto> seatList;
}
