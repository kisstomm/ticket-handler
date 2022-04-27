package tickethandler.partner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tickethandler.partner.model.Seat;
import tickethandler.partner.repository.SeatRepository;


@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;


    public Seat getSeatById(Long seatId) {
        Seat seat = seatRepository.findById(seatId).orElse(null);

        return seat;
    }

    public Seat save(Seat seat) {
        return seatRepository.save(seat);
    }
}
