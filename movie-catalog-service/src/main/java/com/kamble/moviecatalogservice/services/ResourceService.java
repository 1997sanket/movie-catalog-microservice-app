package com.kamble.moviecatalogservice.services;

import java.util.Arrays;

import com.kamble.moviecatalogservice.models.Resource;
import com.kamble.moviecatalogservice.models.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ResourceService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getResourceFallback",
            // Bulkhead pattern to create a separate thread pool for this service
            threadPoolKey = "resourceThreadPool", threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "20"), // Pool will have 20 threads
                    @HystrixProperty(name = "maxQueueSize", value = "10") // After 20 request, queue for 10 more
            })
    public Resource getResource(String userId) {
        return restTemplate.getForObject("http://user-rating-service/ratings/" + userId, Resource.class);
    }

    private Resource getResourceFallback(String userId) {
        // Returning hardcoded data
        return new Resource(Arrays.asList(new UserRating(userId, "0", 0)));
    }

}
