package academy.mindswap.persistence.repositories;


import academy.mindswap.persistence.models.Movie;
import academy.mindswap.persistence.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    Review findByMovie(Movie movie);
}
