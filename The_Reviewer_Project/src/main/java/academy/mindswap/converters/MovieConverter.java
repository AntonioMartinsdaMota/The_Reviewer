package academy.mindswap.converters;

import academy.mindswap.commands.*;
import academy.mindswap.persistence.models.Movie;
import academy.mindswap.persistence.models.Review;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;

public class MovieConverter {

    @Autowired
    private ModelMapper modelMapper;

    public MovieDto convertToDto(Movie movie) {
        return modelMapper.map(movie, MovieDto.class);
    }

    public Movie convertToEntity(MovieDto movieDto) {
        return modelMapper.map(movieDto, Movie.class);
    }


    public Movie createIMDBMovie(MovieFullCastDto movieFullCastDto, MovieRatingDto movieRatingDto) {
        Movie movie = new Movie();

        movie.setImDbId(movieRatingDto.getImDbId());
        movie.setOriginalTitle(movieRatingDto.getTitle());
        movie.setDirectors(movieFullCastDto.getDirectors().getItems().toString());
        movie.setActors(movieFullCastDto.getActors().stream().limit(5).toString());
        movie.setYear(movieRatingDto.getYear());
        //movie.setType();
        movie.setIMDBRating(movieRatingDto.getImDb());
        movie.setRottenTomatoesRating(movieRatingDto.getRottenTomatoes());
        movie.setReviews(new HashSet<>());

        return movie;
    }

    public Movie createMovieDBMovie(MovieDBTranslationDto movieDBTranslationDto){
        Movie movie = new Movie();

        movie.setPortugueseTitle(movieDBTranslationDto.getTranslations().stream()
                .filter(t -> t.getIso_639_1().equalsIgnoreCase("pt"))
                .filter(t -> t.getIso_3166_1().equalsIgnoreCase("pt"))
                .findFirst().get().getData().stream().findFirst().get().getTitle());
        return movie;
    }
}
