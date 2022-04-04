package academy.mindswap.services;

import academy.mindswap.commands.MovieDto;
import academy.mindswap.converters.MovieConverter;
import academy.mindswap.exceptions.otherExceptions.SessionsNotAvailableException;
import academy.mindswap.persistence.models.Movie;
import academy.mindswap.persistence.repositories.MovieRepository;
import academy.mindswap.exceptions.notFoundExceptions.DirectorNotFoundException;
import academy.mindswap.exceptions.notFoundExceptions.MovieNotFoundException;
import academy.mindswap.exceptions.badRequestExceptions.RatingOutOfRangeException;
import academy.mindswap.exceptions.badRequestExceptions.YearOutOfRangeException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService  {

    //private static final Logger LOGGER = LogManager.getLogger(MovieService.class);

    //@Autowired
    private final MovieRepository movieRepository;

    //@Autowired
    private final MovieConverter movieConverter;

    public MovieDto save(MovieDto movieDto) {
        return movieConverter.convertToDto(movieRepository.save(movieConverter.convertToEntity(movieDto)));
    }

    public List<MovieDto> getAllMovies() {
        List<MovieDto> list = movieRepository.findAll()
                .stream()
                .map(m -> movieConverter.convertToDto(m))
                .collect(Collectors.toList());



        System.out.println(list)
        ;
        return list;
    }

    public MovieDto getMovieByOriginalTitle(String originalTitle){
        Optional<Movie> movieOpt = movieRepository.findByOriginalTitle(originalTitle);
        if (movieOpt.isEmpty()){
            throw new MovieNotFoundException();
    }
        return movieConverter.convertToDto(movieOpt.get());

    }

    public List <MovieDto> getMoviesFromDirector(String director){
        List<Movie> movies = movieRepository.findByDirectorsContaining(director);
        if (movies.isEmpty()) {
           throw new DirectorNotFoundException();
        }
        return movies.stream().map(m -> movieConverter.convertToDto(m)).collect(Collectors.toList());
    }

    public List<MovieDto> getMoviesByImdbRating(Float rating){
        if (rating < 0 || rating > 10) {
            throw new RatingOutOfRangeException();
        }
        return movieRepository.findByImdbRating(rating).stream().map(movieConverter::convertToDto).collect(Collectors.toList());

    }

    public List<MovieDto> getMoviesByRottenTomatoesRating(Integer rating){
        if (rating < 0 || rating > 100) {
            throw new RatingOutOfRangeException();
        }
        return movieRepository.findByRottenTomatoesRating(rating).stream().map(movieConverter::convertToDto).collect(Collectors.toList());

    }

    public List<MovieDto> getMoviesByLocalRating(Float rating){
        if (rating < 1 || rating > 5) {
            throw new RatingOutOfRangeException();
        }
        return movieRepository.findByLocalRating(rating).stream().map(movieConverter::convertToDto).collect(Collectors.toList());

    }

    public List<MovieDto> getMoviesByYear(Integer year){
        if (year < 1900 || year > LocalDate.now().getYear()) {
            throw new YearOutOfRangeException();
        }
        return movieRepository.findByYear(year).stream().map(movieConverter::convertToDto).collect(Collectors.toList());

    }


    public String getSessionsUrl(Integer movieId) {
        String urlBase = "https://cinemas.nos.pt/Filmes/Pages/movie.aspx";

        Optional<Movie> movieOpt = movieRepository.findById(movieId);

        if (movieOpt.isEmpty()){
            throw  new MovieNotFoundException();
        }

        String portugueseTitle = movieOpt.get().getPortugueseTitle();

        if (portugueseTitle.equals("")){
            portugueseTitle = movieOpt.get().getOriginalTitle();
        }

        String urlConverted = urlBase.replaceAll("movie", urlConverterToCinemaNos(portugueseTitle));

        try {
            if(!checkUrlAnswer(urlConverted)){
                throw new SessionsNotAvailableException();
            }
        } catch (IOException e) {
        }

        return urlConverted;
    }

    private Boolean checkUrlAnswer(String convertedUrl) throws IOException {
        URL url = new URL(convertedUrl);
        HttpURLConnection huc = (HttpURLConnection) url.openConnection();
        int responseCode = huc.getResponseCode();
        return ( responseCode == HttpURLConnection.HTTP_OK);
    }


    private String urlConverterToCinemaNos(String title) {

        title = title.toLowerCase().replaceAll(" ", "-")
                .replaceAll("[<>%\\$:;,.!?#%]", "-");

        if (title.contains("--")){
            title = title.replaceAll("--", "-");
            return urlConverterToCinemaNos(title);
        }
        return title;
    }


    public List<MovieDto> getMoviesFromActor(String actor) {
        List<Movie> movies = movieRepository.findByActorsContaining(actor);
        if (movies.isEmpty()) {
            throw new DirectorNotFoundException();
        }
        return movies.stream().map(m -> movieConverter.convertToDto(m)).collect(Collectors.toList());
    }
}
