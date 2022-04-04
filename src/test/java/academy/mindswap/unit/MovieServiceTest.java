package academy.mindswap.unit;

import academy.mindswap.commands.MovieDto;
import academy.mindswap.converters.MovieConverter;
import academy.mindswap.converters.ReviewConverter;
import academy.mindswap.converters.UserConverter;
import academy.mindswap.mockdata.MockedData;
import academy.mindswap.persistence.models.Movie;
import academy.mindswap.persistence.repositories.MovieRepository;
import academy.mindswap.persistence.repositories.RoleRepository;
import academy.mindswap.persistence.repositories.UserRepository;
import academy.mindswap.services.MovieService;
import academy.mindswap.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
public class MovieServiceTest {


    private MovieService movieService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private MovieConverter movieConverter;

    @Mock
    private MovieRepository movieRepository;

    @BeforeEach
    void setUp() {
        modelMapper = new ModelMapper();
        movieConverter = new MovieConverter(modelMapper);
        movieService = new MovieService(movieRepository, movieConverter);

    }

    @Test
    public void test_getAllMovies_Should_Return_Success() {
        //given
        List<Movie> movieList = MockedData.getMockedMovies();
        when(movieRepository.findAll()).thenReturn(movieList);

        //when
        List<MovieDto> expected = movieService.getAllMovies();
        List<MovieDto> actual = MockedData.getMockedMoviesDto(movieList);

        //then
        assert(expected.equals(actual));

    }

    @Test
    public void test_getMovieByOriginalTitle_Should_Return_Success() {
        //given
        Movie movie = MockedData.getMockedMovie();
        when(movieRepository.findByOriginalTitle(movie.getOriginalTitle())).thenReturn(Optional.of(movie));

        //when
        MovieDto expected = movieService.getMovieByOriginalTitle(movie.getOriginalTitle());
        MovieDto actual = MockedData.getMockedMovieDto(movie);

        //then
        assertEquals(expected.toString(), actual.toString());

    }

    @Test
    public void test_getMoviesByDirector_Should_Return_Success() {
        //given
        List<Movie> movieList = MockedData.getMockedMovies();
        when(movieRepository.findByDirectorsContaining(movieList.get(0).getDirectors())).thenReturn(movieList);

        //when
        List<MovieDto> expected = movieService.getMoviesFromDirector(movieList.get(0).getDirectors());
        List<MovieDto> actual = MockedData.getMockedMoviesDto(movieList);

        //then
        assertEquals(expected.toString(), actual.toString());

    }

    @Test
    public void test_getMoviesByImdbRating_Should_Return_Success() {
        //given
        List<Movie> movieList = MockedData.getMockedMovies();
        when(movieRepository.findByImdbRating(movieList.get(0).getIMDBRating())).thenReturn(movieList);

        //when
        List<MovieDto> expected = movieService.getMoviesByImdbRating(movieList.get(0).getIMDBRating());
        List<MovieDto> actual = MockedData.getMockedMoviesDto(movieList);

        //then
        assertEquals(expected.toString(), actual.toString());

    }

    @Test
    public void test_getMoviesFromActors_Should_Return_Success() {
        //given
        List<Movie> movieList = MockedData.getMockedMovies();
        when(movieRepository.findByActorsContaining(movieList.get(0).getActors())).thenReturn(movieList);

        //when
        List<MovieDto> expected = movieService.getMoviesFromActor(movieList.get(0).getActors());
        List<MovieDto> actual = MockedData.getMockedMoviesDto(movieList);

        //then
        assertEquals(expected.toString(), actual.toString());

    }



}
