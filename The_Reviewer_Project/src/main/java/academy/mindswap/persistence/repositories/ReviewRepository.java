package academy.mindswap.persistence.repositories;


import academy.mindswap.persistence.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @Query("SELECT r FROM Review r WHERE r.movieId = movieId")
    Review getReviewByMovieId(Integer movieId);

    @Query("SELECT r FROM Review r WHERE r.reviewId = reviewId")
    Review getReviewById(Integer reviewId);


}
