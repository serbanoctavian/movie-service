package com.typesoft.feign.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponse {

    @JsonProperty(value = "page")
    private int page;

    @JsonProperty(value = "results")
    private List<MovieDetails> results;

    @JsonProperty(value = "total_pages")
    private int totalPages;
}
