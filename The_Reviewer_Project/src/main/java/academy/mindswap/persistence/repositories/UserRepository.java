package academy.mindswap.persistence.repositories;

import academy.mindswap.persistence.models.Review;
import academy.mindswap.persistence.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByName(String username);

    User findByEmail(String email);

    User findByNameAndPassword(String name, String password);

    Optional<User> findByEmailAndPassword(String username, String password);

    List<User> deleteAllUsersById (int userId);

}
