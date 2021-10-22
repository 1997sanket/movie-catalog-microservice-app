package com.kamble.moviecatalogservice.services;

import com.kamble.moviecatalogservice.models.Movie;
import com.kamble.moviecatalogservice.models.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfoService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getMovieFallBack",

            commandProperties = {
                    // wait for 2 seconds atmost for a request to complete
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                    // Check last 5 requests
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                    // If 50% of the last 5 requests fail, then apply circuit breaker
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                    // If a service fails, dont call it for 5 seconds
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"), })
    public Movie getMovie(UserRating rating) {
        return restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
    }

    private Movie getMovieFallBack(UserRating rating) {
        return new Movie("0", "No-Movie", "No-desc");
    }

}
