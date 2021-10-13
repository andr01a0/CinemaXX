package instantcoffee.cinemaxx.controller;

import instantcoffee.cinemaxx.dto.MovieDTO;
import instantcoffee.cinemaxx.dto.MovieDTOCustomer;
import instantcoffee.cinemaxx.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieControllerImp {

    MovieService movieService;

    @Autowired
    public MovieControllerImp(MovieService movieService){
        this.movieService = movieService;
    }

    @GetMapping("/{id}")
    MovieDTOCustomer getMovieInfo(@PathVariable ("id") int id){
        return movieService.getById(id);
    }

}
