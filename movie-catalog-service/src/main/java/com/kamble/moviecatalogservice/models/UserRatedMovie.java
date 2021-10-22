package com.kamble.moviecatalogservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRatedMovie {

    private String movieName;
    private String desc;
    private double rating;

}
