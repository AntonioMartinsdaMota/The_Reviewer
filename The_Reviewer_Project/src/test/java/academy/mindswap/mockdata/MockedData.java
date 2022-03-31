package academy.mindswap.mockdata;


import academy.mindswap.commands.MovieDto;
import academy.mindswap.persistence.models.Movie;
import academy.mindswap.persistence.models.Review;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MockedData {


    public static Movie getMockedMovie() {
        return Movie.builder()
                .movieId(1)
                .actors("Tony")
                .directors("Steven")
                .originalTitle("KNIGHT")
                .portugueseTitle("Trevas")
                .IMDBRating(8)
                .localRating(5)
                .type("Action")
                .year(2008)
                .rottenTomatoesRating(85)
                .imDbId("tt0468569")
                .reviews(new HashSet<>())
                .build();


    }

    public  static MovieDto getMockedMovieDto(Movie movie) {
        return MovieDto.builder()
                .movieID(1)
                .actors("Tony")
                .directors("Steven")
                .originalTitle("KNIGHT")
                .portugueseTitle("Trevas")
                .IMDBRating(8)
                .localRating(5)
                .type("Action")
                .year(2008)
                .rottenTomatoesRating(85)
                //.numberOfReviews(0)
                .build();
    }

    public static List<Movie> getMockedMovies() {
        return List.of(
                Movie.builder()
                        .movieId(1)
                        .actors("Tony")
                        .directors("Steven")
                        .originalTitle("The Dark Knight")
                        .portugueseTitle("O Cavaleiro Das Trevas")
                        .IMDBRating(8)
                        .localRating(5)
                        .type("Action")
                        .year(2008)
                        .rottenTomatoesRating(85)
                        .imDbId("tt0468569")
                        .reviews(new HashSet<>()).build(),
                Movie.builder()
                        .movieId(2)
                        .actors("Joao")
                        .directors("Jonny do Souto")
                        .originalTitle("Gay And Proud")
                        .portugueseTitle("O Gay de Portugal")
                        .IMDBRating(9)
                        .localRating(5)
                        .type("Adult")
                        .year(2020)
                        .rottenTomatoesRating(90)
                        .imDbId("JS696969")
                        .reviews(new HashSet<>()).build()
        );
    }

    public static List<MovieDto> getMockedMoviesDto(List<Movie> movieList) {
        return List.of(
                MovieDto.builder()
                        .movieID(1)
                        .actors("Tony")
                        .directors("Steven")
                        .originalTitle("The Dark Knight")
                        .portugueseTitle("O Cavaleiro Das Trevas")
                        .IMDBRating(8)
                        .localRating(5)
                        .type("Action")
                        .year(2008)
                        .rottenTomatoesRating(85)
                        .numberOfReviews(0)
                        .build(),
                MovieDto.builder()
                        .movieID(2)
                        .actors("Joao")
                        .directors("Jonny do Souto")
                        .originalTitle("Gay And Proud")
                        .portugueseTitle("O Gay de Portugal")
                        .IMDBRating(9)
                        .localRating(5)
                        .type("Adult")
                        .year(2020)
                        .rottenTomatoesRating(90)
                        .numberOfReviews(0).build());

    }
}
