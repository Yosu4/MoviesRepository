package com.movie.service.dao;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class RequestSearchMovieByTitle {
    private String title;
}
