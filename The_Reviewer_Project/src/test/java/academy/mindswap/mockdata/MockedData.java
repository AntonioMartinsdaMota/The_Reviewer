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

    //MOVIES

    public static Movie getMockedMovie() {
        return Movie.builder()
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
                //.type(movie.getType())
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

    //USERS
    public static User getMockedUser1() {

       Set<Review> mockedListReviewsUser1 = new HashSet<>();
       mockedListReviewsUser1.add(Review.builder().reviewId(1).build());

        return User.builder()
                .userId(1)
                .username("user1")
                .password("123")
                .email("j@123.pt")
                .reviews(mockedListReviewsUser1)
                .build();
    }

    public static User getMockedUser2() {

        Set<Review> mockedListReviewsUser2 = new HashSet<>();
        mockedListReviewsUser2.add(Review.builder().reviewId(1).build());

        return User.builder()
                .userId(2)
                .username("user2")
                .password("456")
                .email("j@456.pt")
                .reviews(mockedListReviewsUser2)
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

    public static List<User> getMockedUsers(){

        Set<Review> mockedListReviewsUser1 = new HashSet<>();
        mockedListReviewsUser1.add(Review.builder().reviewId(1).build());

        Set<Review> mockedListReviewsUser2 = new HashSet<>();
        mockedListReviewsUser2.add(Review.builder().reviewId(1).build());

        return List.of(
                User.builder()
                    .userId(1)
                    .username("user1")
                    .password("123")
                    .email("j@123.pt")
                    .reviews(mockedListReviewsUser1)
                    .build(),
                User.builder()
                        .userId(2)
                        .username("user2")
                        .password("456")
                        .email("j@456.pt")
                        .reviews(mockedListReviewsUser2)
                        .build()
        );

    }

    public static List<UserDto> getMockedUsersDto(List<User> userList){

                return List.of(
                    UserDto.builder()
                            .id(userList.get(0).getUserId())
                            .username(userList.get(0).getUsername())
                            .password(userList.get(0).getPassword())
                            .email(userList.get(0).getEmail())
                            .numberOfReviews(1)
                            .build(),
                    UserDto.builder()
                            .id(userList.get(1).getUserId())
                            .username(userList.get(1).getUsername())
                            .password(userList.get(1).getPassword())
                            .email(userList.get(1).getEmail())
                            .numberOfReviews(1)
                            .build()
                );

    }

}
