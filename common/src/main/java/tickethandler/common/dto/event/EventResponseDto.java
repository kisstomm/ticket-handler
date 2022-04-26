package tickethandler.common.dto.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tickethandler.common.dto.BaseResponseDto;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventResponseDto extends BaseResponseDto {
    EventDto eventDto;
}
