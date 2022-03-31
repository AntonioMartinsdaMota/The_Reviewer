package academy.mindswap.acceptance;

import academy.mindswap.commands.MovieDto;
import academy.mindswap.mockdata.MockedData;
import academy.mindswap.persistence.models.Movie;
import academy.mindswap.persistence.repositories.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieControllerTest {

    @MockBean
    private MovieRepository movieRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void test_getMovie_By_Title_ShouldReturn_200() {
        //Given

        Movie movie = MockedData.getMockedMovie();
        when(movieRepository.findByOriginalTitle(movie.getOriginalTitle())).thenReturn(Optional.of(movie));
        String url = "/api/movie/search/KNIGHT";


        //When
        ResponseEntity<MovieDto> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                MovieDto.class);

        //Then
        verify(movieRepository, times(1)).findByOriginalTitle(movie.getOriginalTitle());

        MovieDto expected = MockedData.getMockedMovieDto(movie);
        assertEquals(expected, response.getBody());

    }

    @Test
    public void test_getAllMovies_shouldReturn_200() {
        //Given

        List<Movie> movieList = MockedData.getMockedMovies();
        when(movieRepository.findAll()).thenReturn(movieList);
        String url = "/api/movies";


        //When
        ResponseEntity<List<MovieDto>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                new ParameterizedTypeReference<List<MovieDto>>() {
                });

                //Then
                verify(movieRepository, times(1)).findAll();

        List<MovieDto> expected = MockedData.getMockedMoviesDto(movieList);
        assertEquals(expected, response.getBody());

    }

    @Test
    public void test_getMovies_By_Director_ShouldReturn_200() {
        //Given
        Movie movie = MockedData.getMockedMovie();
        List<Movie> movieList = MockedData.getMockedMovies();
        when(movieRepository.findByDirectorsContaining(movie.getDirectors())).thenReturn(movieList);
        String url = "/movies/search/Steven";


        //When
        ResponseEntity<List<MovieDto>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                new ParameterizedTypeReference<List<MovieDto>>() {
                });

        //Then
        verify(movieRepository, times(1)).findAll();

        List<MovieDto> expected = MockedData.getMockedMoviesDto(movieList);
        assertEquals(expected, response.getBody());

    }





}
