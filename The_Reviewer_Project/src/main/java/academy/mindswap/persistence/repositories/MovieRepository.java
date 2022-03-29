package academy.mindswap.persistence.repositories;

import academy.mindswap.persistence.models.Movie;
import academy.mindswap.persistence.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, String> {

    List<Movie> findByTitle(String title);

}
