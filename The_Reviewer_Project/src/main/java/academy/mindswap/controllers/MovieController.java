package academy.mindswap.controllers;


import academy.mindswap.commands.MovieDto;
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
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/movie/{originalTitle}")
    public ResponseEntity<MovieDto> getMovieByOriginalTitle(String originalTitle) {
        return movieService.getMovieByOriginalTitle(originalTitle);
    }

    @GetMapping("/movie/{director}")
    public ResponseEntity<MovieDto> getMovieByDirector(String director) {
        return movieService.getMovieByDirector(director);
    }

    @GetMapping("/movie/{gender}")
    public ResponseEntity<MovieDto> getMovieByGenre(String gender) {
        return movieService.getMovieByGenre(gender);
    }

    @GetMapping("/movie/imdb")
    public ResponseEntity<List<MovieDto>> getMoviesByImdb(@RequestParam(value = "rating") float rating) {
        return movieService.getMoviesByIMDB(rating);
    }

    @GetMapping("/movie/rottenTomatoes")
    public ResponseEntity<List<MovieDto>> getMoviesByRottenTomatoes(@RequestParam(value = "rating") Integer rating) {
        return movieService.getMoviesByRottenTomatoes(rating);
    }

    @GetMapping("/movie/localRating")
    public ResponseEntity<List<MovieDto>> getMoviesByLocalRating(@RequestParam(value = "rating") float rating) {
        return movieService.getMoviesByLocalRating(rating);
    }

    @GetMapping("/movie/{year}")
    public ResponseEntity<List<MovieDto>> getMoviesByYear(@RequestParam(value = "year") Integer year) {
        return movieService.getMoviesByYear(year);
    }





}
