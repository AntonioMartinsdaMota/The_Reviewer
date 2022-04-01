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
@RequestMapping("/api/movie")
@RequiredArgsConstructor
public class MovieController {

    @Autowired
    private MovieService movieService;


    /**
     * Getters
     */

    @GetMapping("/allmovies")//ALL
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        List<MovieDto> movies = movieService.getAllMovies();
        System.out.println(movies);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/searchtitle/{originalTitle}")//ALL
    public ResponseEntity<MovieDto> getMoviesByOriginalTitle(@PathVariable String originalTitle) {
        MovieDto movieDto = movieService.getMovieByOriginalTitle(originalTitle.toUpperCase());
        return ResponseEntity.ok(movieDto);
    }

    @GetMapping("/searchdirector/{director}")//ALL
    public ResponseEntity<List<MovieDto>> getMoviesByDirector(@PathVariable String director) {
        return ResponseEntity.ok(movieService.getMoviesFromDirector(director));
    }

    //SEARCHBYACTORS

    @GetMapping("/imdb")//ALL
    public ResponseEntity<List<MovieDto>> getMoviesByImdb(@RequestParam(value = "rating") float rating) {
        List<MovieDto> movieDtoList = movieService.getMoviesByImdbRating(rating);
        return ResponseEntity.ok(movieDtoList);
    }
    @GetMapping("/rottentomatoes")//ALL
    public ResponseEntity<List<MovieDto>> getMoviesByRottenTomatoes(@RequestParam(value = "rating") Integer rating) {
        List<MovieDto> movieDtoList = movieService.getMoviesByRottenTomatoesRating(rating);
        return ResponseEntity.ok(movieDtoList);
    }
    @GetMapping("/localrating")//ALL
    public ResponseEntity<List<MovieDto>> getMoviesByLocalRating(@RequestParam(value = "rating") float rating) {
        List<MovieDto> movieDtoList = movieService.getMoviesByLocalRating(rating);
        return ResponseEntity.ok(movieDtoList);
    }

    @GetMapping("/searchyear")//ALL
    public ResponseEntity<List<MovieDto>> getMoviesByYear(@RequestParam(value = "year") Integer year) {
        List<MovieDto> movieDtoList = movieService.getMoviesByYear(year);
        return ResponseEntity.ok(movieDtoList);
    }

    //SESSÕES
}
