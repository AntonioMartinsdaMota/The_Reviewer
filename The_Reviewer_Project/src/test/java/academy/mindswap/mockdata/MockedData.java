package academy.mindswap.mockdata;


import academy.mindswap.commands.MovieDto;
import academy.mindswap.commands.UserDto;
import academy.mindswap.persistence.models.Movie;
import academy.mindswap.persistence.models.Review;
import academy.mindswap.persistence.models.User;

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
                .IMDBRating(8f)
                .localRating(5f)
                .type("Action")
                .year(2008)
                .rottenTomatoesRating(85)
                .imDbId("tt0468569")
                //.reviews(new HashSet<>())
                .build();


    }

    public static MovieDto getMockedMovieDto(Movie movie) {
        return MovieDto.builder()
                .movieID(movie.getMovieId())
                .actors(movie.getActors())
                .directors(movie.getDirectors())
                .originalTitle(movie.getOriginalTitle())
                .portugueseTitle(movie.getPortugueseTitle())
                .IMDBRating(movie.getIMDBRating())
                .localRating(movie.getLocalRating())
                .type(movie.getType())
                .year(movie.getYear())
                .rottenTomatoesRating(movie.getRottenTomatoesRating())
                .build();

        //.numberOfReviews(0)

    }

    public static List<Movie> getMockedMovies() {
        return List.of(
                Movie.builder()
                        .movieId(1)
                        .actors("Tony")
                        .directors("Steven")
                        .originalTitle("The Dark Knight")
                        .portugueseTitle("O Cavaleiro Das Trevas")
                        .IMDBRating(8f)
                        .localRating(5f)
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
                        .IMDBRating(9f)
                        .localRating(5f)
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
                        .IMDBRating(8f)
                        .localRating(5f)
                        .type("Action")
                        .year(2008)
                        .rottenTomatoesRating(85)
                        //.numberOfReviews(0)
                        .build(),
                MovieDto.builder()
                        .movieID(2)
                        .actors("Joao")
                        .directors("Jonny do Souto")
                        .originalTitle("Gay And Proud")
                        .portugueseTitle("O Gay de Portugal")
                        .IMDBRating(9f)
                        .localRating(5f)
                        .type("Adult")
                        .year(2020)
                        .rottenTomatoesRating(90)
                        //.numberOfReviews(0)
                        .build());

    }

    public static User getMockedUser() {
        return User.builder()
                .userId(1)
                .username("user1")
                .password("123")
                .email("j@123.pt").build();
        //.reviews(new HashSet<>());
    }

    public static UserDto getMockedUserDto(User user) {
        return UserDto.builder()
                .id(user.getUserId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .build();
    }
}
