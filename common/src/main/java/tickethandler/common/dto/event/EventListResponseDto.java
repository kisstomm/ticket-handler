package tickethandler.common.dto.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tickethandler.common.dto.BaseResponseDto;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventListResponseDto extends BaseResponseDto {
    List<EventDto> eventDtoList;
}
