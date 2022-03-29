package academy.mindswap.converters;

import academy.mindswap.commands.MovieDto;
import academy.mindswap.commands.MovieFullCastDto;
import academy.mindswap.commands.MovieRatingDto;
import academy.mindswap.persistence.models.Movie;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class MovieConverter {

    @Autowired
    private ModelMapper modelMapper;

    public MovieDto convertToDto(Movie movie) {
        return modelMapper.map(movie, MovieDto.class);
    }

    public Movie convertToEntity(MovieDto movieDto) {
        return modelMapper.map(movieDto, Movie.class);
    }


    public Movie createMovie(MovieFullCastDto movieFullCastDto, MovieRatingDto movieRatingDto) {
        Movie movie = new Movie();


        return movie;
    }
}
