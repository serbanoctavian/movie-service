package com.typesoft.feign.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDetails {

    @JsonProperty(value = "original_title")
    private String originalTitle;

    @JsonProperty(value = "overview")
    private String description;

    @JsonProperty(value = "release_date")
    private String releaseDate;

    @JsonProperty(value = "vote_average")
    private Double voteAverage;
}
