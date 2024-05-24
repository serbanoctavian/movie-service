package com.typesoft.service;

import com.typesoft.api.model.MovieModel;

import java.util.List;

public interface SearchService {

    List<MovieModel> searchMovie(String movieName, String username);
}
