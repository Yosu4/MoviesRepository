package com.movie.service.dao;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
public class RequestAddMovie {
    private String title;
    private String description;
    private BigDecimal rating;
    private String image;
}
