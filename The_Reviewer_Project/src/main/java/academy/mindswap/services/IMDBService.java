package academy.mindswap.services;

import academy.mindswap.commands.*;
import academy.mindswap.converters.MovieConverter;
import academy.mindswap.persistence.models.Movie;
import academy.mindswap.exceptions.notFoundExceptions.MovieNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Optional;


@Service
public class IMDBService {

    private static final String IMDB_GETID_URL = "https://imdb-api.com/en/API/SearchMovie/k_lxas8bq6/%s";
    private static final String IMDB_GETRATINGS_URL = "https://imdb-api.com/en/API/Ratings/k_lxas8bq6/%s";
    private static final String IMDB_GETFULLCAST_URL = "https://imdb-api.com/en/API/FullCast/k_lxas8bq6/%s";

    private final RestTemplate restTemplate;

    @Autowired
    private MovieConverter movieConverter;

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


    //@Async
    private String findIMDBID(String movieName) {

        String url = getGetIdUrl(movieName);
        MovieIMDBIDDto result = restTemplate.getForObject(url, MovieIMDBIDDto.class);
        Optional<ResultsIMDBDto> resultsDtoOpt =result.getResults().stream().findFirst();

        if(resultsDtoOpt.isEmpty()){
            throw new MovieNotFoundException();
        }

        return resultsDtoOpt.get().getId().toString();
    }

    //@Async
    private MovieRatingDto findIMDBRatings(String movieName){

        String movieID = findIMDBID(movieName);
        String url = getImdbGetRatingsUrl(movieID);

        MovieRatingDto ratingDto = restTemplate.getForObject(url, MovieRatingDto.class);

        return ratingDto;
    }

    //@Async
   private MovieFullCastDto findIMDBFullCast(String movieName){

        String movieID = findIMDBID(movieName);
        String url = getImdbGetFullCastUrl(movieID);

        MovieFullCastDto fullCastDto = restTemplate.getForObject(url, MovieFullCastDto.class);

        return fullCastDto;
    }

    public Movie createMovieFromIMDB(String movieName){

        MovieRatingDto movieRatingDto = findIMDBRatings(movieName);
        MovieFullCastDto movieFullCastDto = findIMDBFullCast(movieName);
        Movie movie = movieConverter.createIMDBMovie(movieFullCastDto, movieRatingDto);

        return movie;
    }



}