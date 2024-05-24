package com.typesoft.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.typesoft.api.exception.MovieException;
import com.typesoft.api.mapper.MovieModelMapper;
import com.typesoft.api.model.MovieModel;
import com.typesoft.entity.MovieAudit;
import com.typesoft.feign.MovieDatabaseClient;
import com.typesoft.feign.model.MovieDetails;
import com.typesoft.feign.model.MovieResponse;
import com.typesoft.repository.MovieAuditRepository;
import com.typesoft.service.SearchService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final MovieDatabaseClient movieDatabaseClient;
    private final MovieAuditRepository movieAuditRepository;

    @Value("${the-movie-database.api-key}")
    private String apiKey;

    public SearchServiceImpl(MovieDatabaseClient movieDatabaseClient, MovieAuditRepository movieAuditRepository) {
        this.movieDatabaseClient = movieDatabaseClient;
        this.movieAuditRepository = movieAuditRepository;
    }

    @Override
    public List<MovieModel> searchMovie(String movieName, String username) {
        List<MovieDetails> results = getMovieDetails(movieName);
        List<MovieModel> movieModels = results.stream()
                .map(MovieModelMapper::mapFrom)
                .collect(Collectors.toList());

        saveInMovieAudit(movieName, username, movieModels);

        return movieModels;
    }

    private void saveInMovieAudit(String movieName, String username, List<MovieModel> movieModels) {
        try {
            String response = OBJECT_MAPPER.writeValueAsString(movieModels);
            movieAuditRepository.save(new MovieAudit(username, movieName, response));
        } catch (JsonProcessingException e) {
            throw new MovieException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    String.format("Unable to save movie details list as string. Reason: %s", e.getMessage()));
        }
    }

    private List<MovieDetails> getMovieDetails(String movieName) {
        MovieResponse movieResponse = getMovieResponse(movieName, 1);
        int totalPages = movieResponse.getTotalPages();
        List<MovieDetails> results = movieResponse.getResults();
        while (movieResponse.getPage() != totalPages) {
            movieResponse = getMovieResponse(movieName, movieResponse.getPage() + 1);
            results.addAll(movieResponse.getResults());
        }

        return results;
    }

    private MovieResponse getMovieResponse(String movieName, int page) {
        return movieDatabaseClient.getMovie(movieName, apiKey, page).getBody();
    }
}
