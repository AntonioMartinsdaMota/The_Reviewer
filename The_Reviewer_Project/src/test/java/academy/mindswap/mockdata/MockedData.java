package academy.mindswap.mockdata;


import academy.mindswap.commands.MovieDto;
import academy.mindswap.commands.ReviewDto;
import academy.mindswap.commands.UserDto;
import academy.mindswap.persistence.models.Movie;
import academy.mindswap.persistence.models.Review;
import academy.mindswap.persistence.models.User;
import org.springframework.http.ResponseCookie;

import javax.servlet.http.Cookie;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static academy.mindswap.services.CookiesService.AUTH_COOKIE;

public class MockedData {



    public static Movie getMockedMovie() {


        return Movie.builder()
                .movieId(1)
                .actors("Tony")
                .directors("Steven")
                .originalTitle("DUNE")
                .portugueseTitle("Trevas")
                .IMDBRating(8)
                .localRating(5)
                // .type("Action")
                .year(2008)
                .rottenTomatoesRating(85)
                .imDbId("tt0468569")
                .reviews(new HashSet<>())
                .build();


    }
    public static Movie getMockedMovie1() {


        return Movie.builder()
                .movieId(1)
                .actors("Tony")
                .directors("Johny")
                .originalTitle("HAPPY")
                .portugueseTitle("Feliz")
                .IMDBRating(5)
                .localRating(2)
                // .type("Action")
                .year(2020)
                .rottenTomatoesRating(65)
                .imDbId("tt0468888")
                .reviews(new HashSet<>())
                .build();


    }

    public static Movie getMockedMovie2() {
        return Movie.builder()
                .movieId(2)
                .actors("Joao")
                .directors("Maria")
                .originalTitle("MERMAID")
                .portugueseTitle("Sereia")
                .IMDBRating(9)
                .localRating(6)
                //.type("Action")
                .year(2009)
                .rottenTomatoesRating(86)
                .imDbId("tt0465555")
                .reviews(new HashSet<>())
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
                //.type(movie.getType())
                .year(movie.getYear())
                .rottenTomatoesRating(movie.getRottenTomatoesRating())
                .build();


    }

    public static MovieDto getMockedMovieDto1(Movie movie) {
        return MovieDto.builder()
                .movieID(movie.getMovieId())
                .actors(movie.getActors())
                .directors(movie.getDirectors())
                .originalTitle(movie.getOriginalTitle())
                .portugueseTitle(movie.getPortugueseTitle())
                .IMDBRating(movie.getIMDBRating())
                .localRating(movie.getLocalRating())
                .year(movie.getYear())
                .rottenTomatoesRating(movie.getRottenTomatoesRating())
                .numberOfReviews(0)
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
                        //.type("Action")
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
                        // .type("Adult")
                        .year(2020)
                        .rottenTomatoesRating(90)
                        .imDbId("JS696969")
                        .reviews(new HashSet<>()).build()
        );
    }

    public static List<MovieDto> getMockedMoviesDto(List<Movie> movieList) {
        return List.of(
                MovieDto.builder()
                        .movieID(movieList.get(0).getMovieId())
                        .actors(movieList.get(0).getActors())
                        .directors(movieList.get(0).getDirectors())
                        .originalTitle(movieList.get(0).getOriginalTitle())
                        .portugueseTitle(movieList.get(0).getPortugueseTitle())
                        .IMDBRating(movieList.get(0).getIMDBRating())
                        .localRating(movieList.get(0).getLocalRating())
                        .type(movieList.get(0).getType())
                        .year(movieList.get(0).getYear())
                        .rottenTomatoesRating(movieList.get(0).getRottenTomatoesRating())
                        .numberOfReviews(movieList.get(0).getReviews().size())
                        .build(),
                MovieDto.builder()
                        .movieID(movieList.get(1).getMovieId())
                        .actors(movieList.get(1).getActors())
                        .directors(movieList.get(1).getDirectors())
                        .originalTitle(movieList.get(1).getOriginalTitle())
                        .portugueseTitle(movieList.get(1).getPortugueseTitle())
                        .IMDBRating(movieList.get(1).getIMDBRating())
                        .localRating(movieList.get(1).getLocalRating())
                        .type(movieList.get(1).getType())
                        .year(movieList.get(1).getYear())
                        .rottenTomatoesRating(movieList.get(1).getRottenTomatoesRating())
                        .numberOfReviews(movieList.get(1).getReviews().size())
                        .build()
        );

    }

    public static User getMockedUser() {
        Set<Review> mockedListReviews = new HashSet<>();
        mockedListReviews.add(Review.builder().reviewId(1).build());
        return User.builder()
                .userId(1)
                .username("user1")
                .password("123")
                .email("j@123.pt")
                .reviews(mockedListReviews).build();
    }

    public static User getMockedUser2() {
        Set<Review> mockedListReviews = new HashSet<>();
        mockedListReviews.add(Review.builder().reviewId(2).build());
        return User.builder()
                .userId(2)
                .username("user2")
                .password("12345")
                .email("a@123.pt")
                .reviews(mockedListReviews)
                .build();
    }

    public static UserDto getMockedUserDto(User user) {
        return UserDto.builder()
                .id(user.getUserId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .numberOfReviews(user.getReviews().size())
                .build();
    }

    public static Review getMockedReview() {
        return Review.builder()
                .reviewId(1)
                .movie(getMockedMovie())
                .user(getMockedUser())
                .description("The best movie ever")
                .localRating(5)
                .build();
    }

    public static List<Review> getMockedReviewList() {

        return List.of(
                Review.builder()
                        .reviewId(1)
                        .movie(getMockedMovie())
                        .user(getMockedUser())
                        .description("The best movie ever")
                        .localRating(5)
                        .build(),
                Review.builder()
                        .reviewId(2)
                        .movie(getMockedMovie2())
                        .user(getMockedUser2())
                        .description("The worst movie ever")
                        .localRating(1)
                        .build()
        );
    }

    /*public static Set<Review> getMockedReviewSet() {

        return Set.of(
                Review.builder()
                        .reviewId(1)
                        .movie(Movie.builder()
                                .movieId(1)
                                .actors("Tony")
                                .directors("Steven")
                                .originalTitle("KNIGHT")
                                .portugueseTitle("Trevas")
                                .IMDBRating(8)
                                .localRating(5)
                                // .type("Action")
                                .year(2008)
                                .rottenTomatoesRating(85)
                                .imDbId("tt0468569")
                                .reviews(new HashSet<>())
                                .build())
                        .user(User.builder()
                                .userId(1)
                                .username("user1")
                                .password("123")
                                .email("j@123.pt")
                                .reviews(new HashSet<>()).build())
                        .description("The best movie ever")
                        .localRating(5)
                        .build(),
                Review.builder()
                        .reviewId(2)
                        .movie(Movie.builder()
                                .movieId(1)
                                .actors("Tony")
                                .directors("Steven")
                                .originalTitle("KNIGHT")
                                .portugueseTitle("Trevas")
                                .IMDBRating(8)
                                .localRating(5)
                                // .type("Action")
                                .year(2008)
                                .rottenTomatoesRating(85)
                                .imDbId("tt0468569")
                                .reviews(new HashSet<>())
                                .build())
                        .user(User.builder()
                                .userId(1)
                                .username("user1")
                                .password("123")
                                .email("j@123.pt")
                                .reviews(new HashSet<>()).build())
                        .description("The worst movie ever")
                        .localRating(1)
                        .build()
        );
    }*/

    public static ReviewDto getMockedReviewDto(Review review) {
        return ReviewDto.builder()
                .reviewId(review.getReviewId())
                .movieName(review.getMovie().getOriginalTitle())
                .description(review.getDescription())
                .localRating(review.getLocalRating())
                .userName(review.getUser().getUsername())
                .build();

    }

    public static List<ReviewDto> getMockedReviewListDto(List<Review> reviewList) {
        return List.of(
                ReviewDto.builder()
                        .reviewId(reviewList.get(0).getReviewId())
                        .movieName(reviewList.get(0).getMovie().getOriginalTitle())
                        .description(reviewList.get(0).getDescription())
                        .localRating(reviewList.get(0).getLocalRating())
                        .userName(reviewList.get(0).getUser().getUsername())
                        .build(),
                ReviewDto.builder().reviewId(reviewList.get(1).getReviewId())
                        .movieName(reviewList.get(1).getMovie().getOriginalTitle())
                        .description(reviewList.get(1).getDescription())
                        .localRating(reviewList.get(1).getLocalRating())
                        .userName(reviewList.get(1).getUser().getUsername())
                        .build()
        );
    }

    public static Cookie getMockedCookie() {
        Cookie cookie = new Cookie(AUTH_COOKIE, "1");
        return cookie;

    }


}


