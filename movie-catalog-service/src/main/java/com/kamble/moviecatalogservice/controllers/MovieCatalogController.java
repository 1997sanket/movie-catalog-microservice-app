package com.kamble.moviecatalogservice.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.kamble.moviecatalogservice.models.Movie;
import com.kamble.moviecatalogservice.models.Resource;
import com.kamble.moviecatalogservice.models.UserRatedMovie;
import com.kamble.moviecatalogservice.services.MovieInfoService;
import com.kamble.moviecatalogservice.services.ResourceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-movies")
public class MovieCatalogController {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private MovieInfoService movieInfoService;

    // @Autowired
    // private WebClient.Builder webClientBuilder;

    /*
     * We are using the Circuit Breaker pattern in this microservice, because this
     * is the main microservice, who talks to two other microservices and returns
     * consolidated data.
     * 
     * Circuit breaker pattern can be used anywhere where REST call could be slow or
     * failure prone.
     */
    @GetMapping("/{userId}")
    /*
     * @HystrixCommand(fallbackMethod = "getMoviesFallback") -> Don't need this, we
     * shifted granular fallBack methods to their classes
     */
    public List<UserRatedMovie> getMovies(@PathVariable("userId") String userId) {

        // Get List of UserRated movies wrapped inside a Resource object
        Resource resource = resourceService.getResource(userId);
        System.out.println(resource);

        // For each movie ID call movie-info service, get the movie, consolidate the
        // data in UserRatedMovie object and return list
        return resource.getRatings().stream().map(rating -> {
            Movie movie = movieInfoService.getMovie(rating);
            System.out.println(movie);
            UserRatedMovie userRatedMovie = new UserRatedMovie(movie.getMovieName(), movie.getDesc(),
                    rating.getRating());

            System.out.println(userRatedMovie);

            return userRatedMovie;
        }).collect(Collectors.toList());
    }
}
