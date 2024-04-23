package com.movie.service.service;

import com.movie.service.dao.RequestAddMovie;
import com.movie.service.dao.ResponseDao;
import com.movie.service.model.Movies;
import com.movie.service.repository.MoviesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class MovieService {

    @Autowired
    MoviesRepository moviesRepository;

    public ResponseDao addMovie(RequestAddMovie addMovie) {
        try {
            Movies movies = new Movies();
            movies.setDescription(addMovie.getDescription());
            movies.setImage(addMovie.getImage());
            movies.setRating(addMovie.getRating());
            movies.setTitle(addMovie.getTitle());
            movies.setUpdatedBy(new Date());
            movies.setCreatedAt(new Date());
            movies.setIsDelete(false);

            moviesRepository.save(movies);

            return ResponseDao.builder()
                    .bodyResponse(null)
                    .status("Success")
                    .statusCode("200")
                    .build();
        } catch (Exception e){
            log.error("AddMovie(), error message : "+e.getMessage());
            return ResponseDao.builder()
                    .bodyResponse(e.getMessage())
                    .status("Success")
                    .statusCode("200")
                    .build();
        }
    }

    public ResponseDao getAllMovies() {
        Iterable<Movies> movies = moviesRepository.findActiveMovies();
        return ResponseDao.builder()
                .bodyResponse(StreamSupport.stream(movies.spliterator(), false).collect(Collectors.toList()))
                .status("Success")
                .statusCode("200")
                .build();
    }

    public ResponseDao findMovieById(Long id) {
        try{
            Optional<Movies> movie = moviesRepository.findById(id);
            return ResponseDao.builder()
                    .bodyResponse(movie.orElse(null))
                    .status("Success")
                    .statusCode("200")
                    .build();
        } catch (Exception e){
            log.error("findMovieById(), with id : "+ id +" error message : "+e.getMessage());
            return ResponseDao.builder()
                    .bodyResponse("Contact Admin")
                    .status("Failed")
                    .statusCode("400")
                    .build();
        }

    }

    public List<Movies> findMoviesByTitle(String title) {
        List<Movies> movies = moviesRepository.findMoviesByTitleKeyword(title.toLowerCase());
        return movies;
    }

    public ResponseDao deleteMovie(Long id) {
        try{
            Optional<Movies> movies = moviesRepository.findById(id);
            Movies updatedModel = movies.get();
            updatedModel.setIsDelete(true);
            moviesRepository.save(updatedModel);

            return ResponseDao.builder()
                    .status("Success")
                    .statusCode("200")
                    .bodyResponse("Movies with ID : " + updatedModel.getId() + " is deleted")
                    .build();
        } catch (Exception e){
            log.error("deleteMovie(), with id : "+ id +" error message : "+e.getMessage());
            return ResponseDao.builder()
                    .status("Failed")
                    .statusCode("400")
                    .bodyResponse("Please contact admin")
                    .build();
        }

    }
}
