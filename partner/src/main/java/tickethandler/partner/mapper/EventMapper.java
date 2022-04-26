package tickethandler.partner.mapper;

import org.mapstruct.Mapper;
import tickethandler.common.dto.EventDto;
import tickethandler.partner.model.Event;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {

    /**
        Maps Event List to EventDto List

        @param eventList what contains the data list
        @return eventDtoList what contains dto lis
     */
    List<EventDto> modelListToDtoList(List<Event> eventList);
}
