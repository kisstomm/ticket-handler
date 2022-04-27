package tickethandler.partner.mapper;

import org.mapstruct.Mapper;
import tickethandler.common.dto.event.SeatDto;
import tickethandler.partner.model.Seat;


@Mapper(componentModel = "spring")
public interface SeatMapper {


    /**
     Maps Seat to SeatDto

     @param seat what contains the data
     @return eventDto what contains dto
     */
    SeatDto modelToDto(Seat seat);

}
