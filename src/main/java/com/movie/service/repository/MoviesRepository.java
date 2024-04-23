package com.movie.service.repository;

import com.movie.service.model.Movies;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviesRepository extends CrudRepository<Movies, Long> {

    @Query("Select u From Movies u where u.isDelete = false")
    List<Movies> findActiveMovies();

    @Query(value = "SELECT * FROM Movies u where u.is_delete = false and lower(u.title) LIKE %:keyword%", nativeQuery = true)
    List<Movies> findMoviesByTitleKeyword(@Param("keyword") String keyword);
}
