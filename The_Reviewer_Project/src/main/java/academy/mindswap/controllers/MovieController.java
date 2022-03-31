package academy.mindswap.controllers;


import academy.mindswap.commands.MovieDto;
import academy.mindswap.persistence.models.Movie;
import academy.mindswap.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.OneToMany;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MovieController {

    @Autowired
    private MovieService movieService;


    /**
     * Getters
     */

    @GetMapping("/movies")//ALL
    public List<MovieDto> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/movie/search/{originalTitle}")//ALL
    public ResponseEntity<MovieDto> getMoviesByOriginalTitle(@PathVariable String originalTitle) {
        MovieDto movieDto = movieService.getMovieByOriginalTitle(originalTitle.toUpperCase());
        return ResponseEntity.ok(movieDto);
    }

    @GetMapping("/movies/search/{director}")//ALL
    public List<MovieDto> getMovieByDirector(@PathVariable String director) {
        return movieService.getMoviesFromDirector(director);
    }

   /* @GetMapping("/movie/{gender}")
    public ResponseEntity<Movie> getMovieByGenre(String gender) {
        return movieService.getMovieByGender(gender);
    }*/

    @GetMapping("/movie/imdb")//ALL
    public ResponseEntity<List<MovieDto>> getMoviesByImdb(@RequestParam(value = "rating") float rating) {
        List<MovieDto> movieDtoList = movieService.getMoviesByImdbRating(rating);
        return ResponseEntity.ok(movieDtoList);
    }
    @GetMapping("/movie/rottenTomatoes")//ALL
    public ResponseEntity<List<MovieDto>> getMoviesByRottenTomatoes(@RequestParam(value = "rating") Integer rating) {
        List<MovieDto> movieDtoList = movieService.getMoviesByRottenTomatoesRating(rating);
        return ResponseEntity.ok(movieDtoList);
    }
    @GetMapping("/movie/localRating")//ALL
    public ResponseEntity<List<MovieDto>> getMoviesByLocalRating(@RequestParam(value = "rating") float rating) {
        List<MovieDto> movieDtoList = movieService.getMoviesByLocalRating(rating);
        return ResponseEntity.ok(movieDtoList);
    }

    @GetMapping("/movie")//ALL
    public ResponseEntity<List<MovieDto>> getMoviesByYear(@RequestParam(value = "year") Integer year) {
        List<MovieDto> movieDtoList = movieService.getMoviesByYear(year);
        return ResponseEntity.ok(movieDtoList);
    }
}
