package instantcoffee.cinemaxx.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "theater_halls", schema = "cinemaxx")
public class TheaterHall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int theaterHallId;

    //@OneToMany(mappedBy = "")
    //List<TimeSlot> timeSlots;
}
