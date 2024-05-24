package com.typesoft.api.mapper;

import com.typesoft.api.model.MovieModel;
import com.typesoft.feign.model.MovieDetails;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MovieModelMapper {

    public static MovieModel mapFrom(MovieDetails movieDetails) {
        return MovieModel.builder()
                .title(movieDetails.getOriginalTitle())
                .releaseDate(movieDetails.getReleaseDate())
                .description(movieDetails.getDescription())
                .rating(String.valueOf(movieDetails.getVoteAverage()))
                .build();
    }
}
