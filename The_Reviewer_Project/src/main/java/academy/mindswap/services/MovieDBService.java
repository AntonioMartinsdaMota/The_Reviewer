package academy.mindswap.services;

import academy.mindswap.commands.MovieFullCastDto;
import academy.mindswap.commands.MovieIDDto;
import academy.mindswap.commands.MovieRatingDto;
import academy.mindswap.commands.ResultsDto;
import academy.mindswap.converters.MovieConverter;
import academy.mindswap.persistence.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class MovieDBService {

    private static final String MOVIEDB_GETID_URL =
            "https://api.themoviedb.org/3/search/movie?api_key=fdd6b150993a41798542135c559aba0a&query=%s";
    private static final String MOVIEDB_GET_TRANSLATIONS_URL =
            "https://api.themoviedb.org/3/movie/%s/translations?api_key=fdd6b150993a41798542135c559aba0a";

    private final RestTemplate restTemplate;

    @Autowired
    private MovieConverter movieConverter;

    public MovieDBService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String getGetIdUrl(String movieName) {
        movieName.replaceAll(" ", "%20");
        return String.format(MOVIEDB_GETID_URL, movieName);
    }
    public String getMovieDBGetRatingsUrl(String movieId) {
        return String.format(MOVIEDB_GET_TRANSLATIONS_URL, movieId);
    }


    //@Async
    private String findMovieDBID(String movieName) throws MovieNotFoundException{

        String url = getGetIdUrl(movieName);
        MovieIDDto result = restTemplate.getForObject(url, MovieIDDto.class);
        Optional<ResultsDto> resultsDtoOpt =result.getResults().stream().findFirst();

        if(resultsDtoOpt.isEmpty()){
            throw new MovieNotFoundException();
        }

        return resultsDtoOpt.get().getId();
    }

    //@Async
    private MovieRatingDto findIMDBRatings(String movieName) throws MovieNotFoundException{

        String movieID = findIMDBID(movieName);
        String url = getImdbGetRatingsUrl(movieID);

        MovieRatingDto ratingDto = restTemplate.getForObject(url, MovieRatingDto.class);

        return ratingDto;
    }

    //@Async
    private MovieFullCastDto findIMDBFullCast(String movieName) throws MovieNotFoundException{

        String movieID = findIMDBID(movieName);
        String url = getImdbGetFullCastUrl(movieID);

        MovieFullCastDto fullCastDto = restTemplate.getForObject(url, MovieFullCastDto.class);

        return fullCastDto;
    }

    public Movie createMovieFromIMDB(String movieName) throws MovieNotFoundException{

        MovieRatingDto movieRatingDto = findIMDBRatings(movieName);
        MovieFullCastDto movieFullCastDto = findIMDBFullCast(movieName);
        Movie movie = movieConverter.createIMDBMovie(movieFullCastDto, movieRatingDto);

        return movie;
    }



}