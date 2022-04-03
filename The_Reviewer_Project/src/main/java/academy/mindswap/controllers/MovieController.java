package academy.mindswap.controllers;


import academy.mindswap.commands.MovieDto;
import academy.mindswap.controllers.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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

    @GetMapping("/searchactor/{actor}")//ALL
    public ResponseEntity<List<MovieDto>> getMoviesByActor(@PathVariable String actor) {
        return ResponseEntity.ok(movieService.getMoviesFromActor(actor));
    }

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

    @GetMapping(value = "/sessions/{movieId}")
    public void getMovieSessions(@PathVariable Integer movieId,  HttpServletResponse httpServletResponse) {
        String url = movieService.getSessionsUrl(movieId);
        httpServletResponse.setHeader("Location", url);
        httpServletResponse.setStatus(302);
    }
}
