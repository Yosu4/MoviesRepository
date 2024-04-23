package com.movie.service.controller;

import com.movie.service.dao.RequestAddMovie;
import com.movie.service.dao.RequestSearchMovieByTitle;
import com.movie.service.dao.ResponseDao;
import com.movie.service.model.Movies;
import com.movie.service.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping("/getMovies")
    @ResponseBody
    private ResponseDao getMovies(){
        log.info("Get all Movies "+MovieController.class.getName());
        return movieService.getAllMovies();
    }

    @PostMapping("/addMovie")
    private ResponseDao createMovies(@RequestBody RequestAddMovie body){
        return movieService.addMovie(body);
    }

    @PostMapping("/findById")
    private ResponseDao findMoviesById(@RequestBody Long id){
        return movieService.findMovieById(id);
    }

    @PostMapping("/findByTitle")
    private List<Movies> findMoviesByTitle(@RequestBody String title){
        return movieService.findMoviesByTitle(title);
    }

    @PostMapping("/delete")
    private ResponseDao deleteById(@RequestBody Long id){
        return movieService.deleteMovie(id);
    }
}
