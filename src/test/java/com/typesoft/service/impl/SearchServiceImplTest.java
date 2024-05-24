package com.typesoft.service.impl;

import com.typesoft.api.model.MovieModel;
import com.typesoft.feign.MovieDatabaseClient;
import com.typesoft.feign.model.MovieDetails;
import com.typesoft.feign.model.MovieResponse;
import com.typesoft.repository.MovieAuditRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SearchServiceImplTest {

    @InjectMocks
    private SearchServiceImpl searchService;

    @Mock
    private MovieDatabaseClient movieDatabaseClient;

    @Mock
    private MovieAuditRepository movieAuditRepository;

    @Test
    void should_return_movie_model_list_by_movie_name() {
        //given
        String movieName = "test";
        String username = "user";
        doReturn(ResponseEntity.of(Optional.of(createMovieResponseForFirstPage())))
                .when(movieDatabaseClient).getMovie(movieName, null, 1);
        doReturn(ResponseEntity.of(Optional.of(createMovieResponseForSecondPage())))
                .when(movieDatabaseClient).getMovie(movieName, null, 2);

        //when
        List<MovieModel> movieModels = searchService.searchMovie(movieName, username);

        //then/assert
        assertEquals(2, movieModels.size());
        verify(movieAuditRepository, times(1)).save(any());
    }

    private MovieResponse createMovieResponseForFirstPage() {
        return MovieResponse.builder()
                .page(1)
                .totalPages(2)
                .results(createMovieDetailsListForFirstPage())
                .build();
    }

    private MovieResponse createMovieResponseForSecondPage() {
        return MovieResponse.builder()
                .page(2)
                .totalPages(2)
                .results(createMovieDetailsListForSecondPage())
                .build();
    }

    private List<MovieDetails> createMovieDetailsListForFirstPage() {
        List<MovieDetails> movieDetails = new ArrayList<>();
        movieDetails.add(MovieDetails.builder()
                .originalTitle("test-1")
                .description("unitary test")
                .releaseDate("2024-05-17")
                .voteAverage(10.0)
                .build());

        return movieDetails;
    }

    private List<MovieDetails> createMovieDetailsListForSecondPage() {
        List<MovieDetails> movieDetails = new ArrayList<>();
        movieDetails.add(MovieDetails.builder()
                .originalTitle("test-2")
                .description("unitary test")
                .releaseDate("2024-05-17")
                .voteAverage(10.0)
                .build());

        return movieDetails;
    }
}