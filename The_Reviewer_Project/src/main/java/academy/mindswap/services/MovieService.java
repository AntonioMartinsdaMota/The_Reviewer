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

import javax.imageio.plugins.tiff.TIFFImageReadParam;
import java.lang.management.OperatingSystemMXBean;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService  {

    //private static final Logger LOGGER = LogManager.getLogger(MovieService.class);

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

    public MovieDto getMovieByOriginalTitle(String originalTitle) throws MovieNotFoundException {
        Optional<Movie> movieOpt = movieRepository.findByOriginalTitle(originalTitle);
        if (movieOpt.isEmpty()){
            throw new MovieNotFoundException();
    }
        return movieConverter.convertToDto(movieOpt.get());

    }

    public List <MovieDto> getMoviesFromDirector(String director) throws DirectorNotFoundException {
        List<Movie> movies = movieRepository.findByDirectorsContaining(director);
        if (movies.isEmpty()) {
           throw new DirectorNotFoundException();
        }
        return movies.stream().map(m -> movieConverter.convertToDto(m)).collect(Collectors.toList());
    }

    /*public List <MovieDto> getMoviesByGender(String gender){
        return movieRepository.findByGender(gender)
                .stream()
                .map(movieConverter::convertToDto)
                .collect(Collectors.toList());
    }*/

    public List<MovieDto> getMoviesByImdbRating(float rating) throws RatingOutOfRangeException {
        if (rating < 0 || rating > 10) {
            throw new RatingOutOfRangeException();
        }
        return movieRepository.findByImdbRating(rating).stream().map(movieConverter::convertToDto).collect(Collectors.toList());

    }

    public List<MovieDto> getMoviesByRottenTomatoesRating(Integer rating) throws RatingOutOfRangeException {
        if (rating < 0 || rating > 100) {
            throw new RatingOutOfRangeException();
        }
        return movieRepository.findByRottenTomatoesRating(rating).stream().map(movieConverter::convertToDto).collect(Collectors.toList());

    }

    public List<MovieDto> getMoviesByLocalRating(float rating) throws RatingOutOfRangeException {
        if (rating < 1 || rating > 5) {
            throw new RatingOutOfRangeException();
        }
        return movieRepository.findByLocalRating(rating).stream().map(movieConverter::convertToDto).collect(Collectors.toList());

    }

    public List<MovieDto> getMoviesByYear(Integer year) throws YearOutOfRangeException {
        if (year < 1900 || year > LocalDate.now().getYear()) {
            throw new YearOutOfRangeException();
        }
        return movieRepository.findByYear(year).stream().map(movieConverter::convertToDto).collect(Collectors.toList());

    }








}
