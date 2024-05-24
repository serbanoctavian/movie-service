package com.typesoft.api.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieModel {

    private String title;
    private String releaseDate;
    private String description;
    private String rating;

    public String getRating() {
        return rating + "/10";
    }
}
