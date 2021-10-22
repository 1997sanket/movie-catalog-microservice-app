package com.kamble.userratingsservice.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRating {

    private String userId;
    private String movieId;
    private double rating;
}
