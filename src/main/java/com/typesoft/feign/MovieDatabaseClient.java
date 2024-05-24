package com.typesoft.feign;

import com.typesoft.feign.model.MovieResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "MovieDatabaseClient", url = "${the-movie-database.url}")
public interface MovieDatabaseClient {

    @GetMapping("/search/movie")
    ResponseEntity<MovieResponse> getMovie(@RequestParam(name = "query") String query,
                                           @RequestParam(name = "api_key") String apiKey,
                                           @RequestParam(name = "page") int page);
}
