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

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/movie/{originalTitle}")
    public ResponseEntity<Movie> getMovieByOriginalTitle(String originalTitle) {
        return movieService.getMovieByOriginalTitle(originalTitle);
    }

    @GetMapping("/movie/{director}")
    public ResponseEntity<Movie> getMovieByDirector(String director) {
        return movieService.getMovieByDirector(director);
    }

    @GetMapping("/movie/{gender}")
    public ResponseEntity<Movie> getMovieByGenre(String gender) {
        return movieService.getMovieByGender(gender);
    }

    @GetMapping("/movie/imdb")
    public ResponseEntity<List<Movie>> getMoviesByImdb(@RequestParam(value = "rating") float rating) {
        return movieService.getMoviesByIMDB(rating);
    }
    @GetMapping("/movie/rottenTomatoes")
    public ResponseEntity<List<Movie>> getMoviesByRottenTomatoes(@RequestParam(value = "rating") Integer rating) {
        return movieService.getMoviesByRottenTomatoes(rating);
    }
    @GetMapping("/movie/localRating")
    public ResponseEntity<List<Movie>> getMoviesByLocalRating(@RequestParam(value = "rating") float rating) {
        return movieService.getMoviesByLocalRating(rating);
    }


}
