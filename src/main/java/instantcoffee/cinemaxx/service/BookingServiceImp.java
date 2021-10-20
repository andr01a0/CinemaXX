package instantcoffee.cinemaxx.service;

import org.springframework.stereotype.Service;

import instantcoffee.cinemaxx.entities.Booking;
import instantcoffee.cinemaxx.entities.Movie;
import instantcoffee.cinemaxx.entities.TheaterHall;
import instantcoffee.cinemaxx.repo.BookingRepo;
import instantcoffee.cinemaxx.repo.MovieRepo;
import instantcoffee.cinemaxx.repo.SeatRepo;
import instantcoffee.cinemaxx.repo.TheaterHallRepo;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookingServiceImp implements BookingService {

    private final BookingRepo bookingRepository;
    private final MovieRepo movieRepository;
    private final TheaterHallRepo theaterHallRepository;
    private final SeatRepo seatRepository;

    @Override
    public Booking createBooking(User user, String theaterHallId, String movieId, String timeSlotId, String seatId) {
        TheaterHall theaterHall = theaterHallRepository
            .findById(Integer.parseInt(theaterHallId))
            .orElseThrow(BadRequestException("Perhaps you should try with a `theaterHallId` that exists?!"));

        Movie movie = theaterHall
            .getMovies()
            .stream()
            .filter(
                movie -> movie.getMovieId() == Integer.parseInt(movieId)
            )
            .findFirst()
            .orElseThrow(BadRequestException("Perhaps you should try with a `movieId` that exists?!"));

        TimeSlot timeSlot = movie
            .getTimeSlots()
            .stream()
            .filter(
                timeSlot -> timeSlot.getTimeSlotId() == Integer.parseInt(timeSlotId)
            )
            .findFirst()
            .orElseThrow(BadRequestException("Perhaps you should try with a `timeSlotId` that exists?!"));

        boolean isSeatTaken = timeSlot
            .getBookings()
            .stream()
            .map(
                booking -> booking.getSeat()).filter(seat -> seat.getSeatId() == Integer.parseInt(seatId)
            )
            .findFirst()
            .isPresent();

        if (isSeatTaken) {
            throw new BadRequestException("You cannot sit with us.");
        }

        Seat seat = theaterHall
            .getSeats()
            .stream()
            .filter(
                seat -> seat.getSeatId() == Integer.parseInt(seatId)
            )
            .findFirst()
            .orElseThrow(BadRequestException("You want to sit outside the Theater Hall or what?"))

        Booking booking = new Booking(user, timeSlot, seat);

        return booking;
    }

    @Override
    public void cancelBooking(int id) {
        System.out.println("cancel booking.");
    }

}
