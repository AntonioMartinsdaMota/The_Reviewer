package academy.mindswap.services;

import academy.mindswap.commands.*;
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
        String movieNameNewFormat = movieName.replaceAll(" ", "%20");
        return String.format(MOVIEDB_GETID_URL, movieNameNewFormat);
    }
    public String getMovieDBGetTranslationsUrl(String movieId) {
        return String.format(MOVIEDB_GET_TRANSLATIONS_URL, movieId);
    }


    //@Async
    private Integer findMovieDBID(String movieName) {

        String url = getGetIdUrl(movieName);
        MovieMovieDBIDDto result = restTemplate.getForObject(url, MovieMovieDBIDDto.class);
        Optional<ResultsMovieDBDto> resultsDtoOpt =result.getResults().stream().findFirst();

        if(resultsDtoOpt.isEmpty()){
            return 0;
        }

        return resultsDtoOpt.get().getId();
    }

    //@Async
    private MovieDBTranslationDto findMovieDBTranslations(String movieName) {

        Integer movieID = findMovieDBID(movieName);
        if (movieID.equals(0)){
            return null;
        }
        String url = getMovieDBGetTranslationsUrl(String.valueOf(movieID));

        MovieDBTranslationDto translationDto = restTemplate.getForObject(url, MovieDBTranslationDto.class);

        return translationDto;
    }

    public Movie createMovieFromMovieDB(String movieName) {

        MovieDBTranslationDto translationDto = findMovieDBTranslations(movieName);

        if (translationDto == null){
            return new Movie();
        }
        Movie movie = movieConverter.createMovieDBMovie(translationDto);

        return movie;
    }



}