package academy.mindswap.services;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.RBTableBuilder;
import java.util.concurrent.CompletableFuture;

@Service
public class IMDBService {

    private static final String IMDB_GETID_URL = "https://imdb-api.com/en/API/SearchMovie/k_lxas8bq6/%s";
    private static final String IMDB_GETRATINGS_URL = "https://imdb-api.com/en/API/Ratings/k_lxas8bq6/%s";
    private static final String IMDB_GETFULLCAST_URL = "https://imdb-api.com/en/API/FullCast/k_lxas8bq6/%s";

    private final RestTemplate restTemplate;

    public IMDBService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String getGetIdUrl(String movieName) {
        return String.format(IMDB_GETID_URL, movieName);
    }
    public String getImdbGetRatingsUrl(String movieId) {
        return String.format(IMDB_GETRATINGS_URL, movieId);
    }
    public String getImdbGetFullCastUrl(String movieId) {
        return String.format(IMDB_GETFULLCAST_URL, movieId);
    }

/*
    @Async
    public CompletableFuture<User> findUser(String user) {
        long start = System.currentTimeMillis();
        System.out.println(("Elapsed Time Started Service: " + user + " " + (System.currentTimeMillis() - start)));
        String url = getGitHubUrl(user);
        User result = restTemplate.getForObject(url, User.class);
        System.out.println(("Elapsed Time Ended Service: " + user + " " + (System.currentTimeMillis() - start)));
        return CompletableFuture.completedFuture(result);
    }
    */

}