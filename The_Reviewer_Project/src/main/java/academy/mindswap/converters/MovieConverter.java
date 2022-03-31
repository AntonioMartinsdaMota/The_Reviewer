package academy.mindswap.converters;

import academy.mindswap.commands.*;
import academy.mindswap.persistence.models.Movie;
import academy.mindswap.persistence.models.Review;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
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
        movie.setOriginalTitle(movieRatingDto.getTitle().toUpperCase());
        movie.setDirectors(movieFullCastDto.getDirectors().getItems().stream().map(d -> d.getName()).collect(Collectors.joining(" ,")));
        movie.setActors(movieFullCastDto.getActors().stream().limit(5).map(a -> a.getName()).collect(Collectors.joining(" ,")));
        movie.setYear(movieRatingDto.getYear());
        //movie.setType();
        movie.setIMDBRating(movieRatingDto.getImDb());
        movie.setRottenTomatoesRating(movieRatingDto.getRottenTomatoes());
        movie.setReviews(new HashSet<>());

        return movie;
    }

    public Movie createMovieDBMovie(MovieDBTranslationDto movieDBTranslationDto){
        Movie movie = new Movie();
        String portugueseTitle="";

        Optional<TranslationsDto> translationsDto = movieDBTranslationDto.getTranslations().stream()
                .filter(t -> t.getIso_639_1().equalsIgnoreCase("pt"))
                .filter(t -> t.getIso_3166_1().equalsIgnoreCase("pt"))
                .findFirst();

        if(translationsDto.isPresent()){
            portugueseTitle = translationsDto.get().getData().getTitle();
        }

        movie.setPortugueseTitle(portugueseTitle);
        return movie;
    }
}
