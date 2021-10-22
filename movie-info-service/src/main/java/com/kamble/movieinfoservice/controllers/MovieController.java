package com.kamble.movieinfoservice.controllers;

import java.util.Arrays;
import java.util.List;

import com.kamble.movieinfoservice.models.Movie;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {

    List<Movie> movies = Arrays.asList(new Movie("0", "No-Movie", "--"), new Movie("M1", "Godfather", "Crime"),
            new Movie("M2", "Hulchul", "Comedy"), new Movie("M3", "Conjuring", "Horror"),
            new Movie("M4", "Inception", "Sci-Fi"), new Movie("M5", "Troy", "Action"));

    @GetMapping("/{movieId}")
    public Movie getMovie(@PathVariable("movieId") String movieId) {
        // return new Movie("M1", "Godfather", "Crime");

        /*
         * Deliberately trying to slow the microservice try { Thread.sleep(4000); }
         * catch (InterruptedException e) { // TODO Auto-generated catch block
         * e.printStackTrace(); }
         */

        return movies.stream().filter(movie -> movie.getMovieId().equals(movieId)).findAny().get();
    }
}
