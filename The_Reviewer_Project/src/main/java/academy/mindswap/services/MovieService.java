package academy.mindswap.services;

import academy.mindswap.commands.MovieDto;
import academy.mindswap.converters.MovieConverter;
import academy.mindswap.persistence.models.Movie;
import academy.mindswap.persistence.repositories.MovieRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.cache.spi.DirectAccessRegion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService  {

    private static final Logger LOGGER = LogManager.getLogger(MovieService.class);

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieConverter movieConverter;

    public MovieDto save(MovieDto movieDto) {
        return movieConverter.convertToDto(movieRepository.save(movieConverter.convertToEntity(movieDto)));
    }

    public List<MovieDto> getAllMovies() {
        return movieRepository.findAll()
                .stream()
                .map(movieConverter::convertToDto)
                .collect(Collectors.toList());
    }

    public MovieDto getMovieByOriginalTitle(String originalTitle) {
        return movieConverter.convertToDto(movieRepository.findByOriginalTitle(originalTitle));

    }

    public List <MovieDto> getMoviesFromDirector(String director) throws DirectorNotFoundException {
        return movieRepository.findByDirector(director)
                .stream()
                .map(movieConverter::convertToDto)
                .collect(Collectors.toList());
    }

    public List <MovieDto> getMoviesByGender(String gender){
        return movieRepository.findByGender(gender)
                .stream()
                .map(movieConverter::convertToDto)
                .collect(Collectors.toList());
    }

    public List<MovieDto> getMoviesByImdb(float rating){
        return movieRepository.findByImdbRating(rating).stream().map(movieConverter::convertToDto).collect(Collectors.toList());

    }

    public List<MovieDto> getMoviesByRottenTomatoes(Integer rating){
        return movieRepository.findByRottenTomatoesRating(rating).stream().map(movieConverter::convertToDto).collect(Collectors.toList());

    }

    public List<MovieDto> getMoviesByLocalRating(Integer rating){
        return movieRepository.findByLocalRating(rating).stream().map(movieConverter::convertToDto).collect(Collectors.toList());

    }

    public List<MovieDto> getMoviesByYear(Integer year){
        return movieRepository.findByYear(year).stream().map(movieConverter::convertToDto).collect(Collectors.toList());

    }








}
