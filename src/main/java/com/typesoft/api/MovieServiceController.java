package com.typesoft.api;

import com.typesoft.api.model.MovieModel;
import com.typesoft.service.SearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/v1/api")
public class MovieServiceController {

    private final SearchService searchService;

    public MovieServiceController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public List<MovieModel> searchMovie(@RequestParam String movieName) {
        return searchService.searchMovie(movieName, "serbanoctavian");
    }
}
