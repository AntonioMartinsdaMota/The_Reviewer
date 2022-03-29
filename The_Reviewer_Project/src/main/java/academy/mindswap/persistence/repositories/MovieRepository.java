package academy.mindswap.persistence.repositories;

import academy.mindswap.persistence.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {


   /* @Query("Select m from Movie m where m.originalTitle = :originalTitle")
    Movie findByOriginalTitle(@Param("originalTitle") String originalTitle);

    @Query("Select m from Movie m where m.director = :director")
    List<Movie> findByDirector(@Param("director") String director);

    @Query("Select m from Movie m where m.gender = :gender")
    List<Movie> findByGender(@Param("genre") String gender);

    @Query("Select m from Movie m where m.year = :year")
    List<Movie> findByYear(@Param("year") int year);*/

    @Query("Select m from Movie m where m.IMDBRating >= :IMDBRating")
    List<Movie> findByImdbRating(@Param("IMDBRating") float IMDBRating);

    @Query("Select m from Movie m where m.rottenTomatoesRating >= :rottenTomatoesRating")
    List<Movie> findByRottenTomatoesRating(@Param("IMDBRating") Integer rottenTomatoesRating);

    @Query("Select m from Movie m where m.localRating >= :localRating")
    List<Movie> findByLocalRating(@Param("localRating") float localRating);


    Movie findByMovieId(Integer movieId);

    Movie findByOriginalTitle(String originalTitle);

    List<Movie> findByDirectorsContaining(String director);

    List<Movie> findByYear(Integer year);
}

