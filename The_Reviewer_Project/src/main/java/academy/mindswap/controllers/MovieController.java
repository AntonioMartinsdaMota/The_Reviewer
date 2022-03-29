package academy.mindswap.controllers;


import academy.mindswap.persistence.models.Movie;
import academy.mindswap.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.OneToMany;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MovieController {

    @Autowired
    private MovieService movieService;


    /**
     * Getters
     */

    @GetMapping("/movies")
    public List<MovieDto> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/movie/{originalTitle}")
    public ResponseEntity<MovieDto> getMoviesByOriginalTitle(@PathVariable String originalTitle) {
        MovieDto movieDto = movieService.getMovieByOriginalTitle(originalTitle);
        return ResponseEntity.ok(movieDto);
    }

    @GetMapping("/movies/{director}")
    public List<MovieDto> getMovieByDirector(String director) {
        return movieService.getMoviesFromDirector(director);
    }

    @GetMapping("/movie/{gender}")
    public ResponseEntity<Movie> getMovieByGenre(String gender) {
        return movieService.getMovieByGender(gender);
    }

    @GetMapping("/movie/imdb")
    public List<MovieDto> getMoviesByImdb(@RequestParam(value = "rating") float rating) {
        return movieService.getMoviesByImdb(rating);
    }
    @GetMapping("/movie/rottenTomatoes")
    public ResponseEntity<List<Movie>> getMoviesByRottenTomatoes(@RequestParam(value = "rating") Integer rating) {
        return movieService.getMoviesByRottenTomatoes(rating);
    }
    @GetMapping("/movie/localRating")
    public List<MovieDto> getMoviesByLocalRating(@RequestParam(value = "rating") Integer rating) {
        return movieService.getMoviesByLocalRating(rating);
    }

    @GetMapping("/movie/{year}")
    public List<MovieDto> getMoviesByYear(@RequestParam(value = "year") Integer year) {
        return movieService.getMoviesByYear(year);
    }


}
