package com.kamble.userratingsservice.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.kamble.userratingsservice.models.UserRating;
import com.kamble.userratingsservice.models.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratings")
public class UserRatingsController {

    private List<UserRating> ratings = Arrays.asList(new UserRating("foo", "M1", 4.5), new UserRating("foo", "M2", 4),
            new UserRating("foo", "M3", 4), new UserRating("moo", "M4", 3), new UserRating("moo", "M5", 1));

    @GetMapping("/{userId}")
    public Resource getMovieRatings(@PathVariable("userId") String userId) {

        List<UserRating> userRatings = ratings.stream().filter(userRating -> userRating.getUserId().equals(userId))
                .collect(Collectors.toList());

        Resource resource = new Resource(userRatings);

        return resource;

    }
}
