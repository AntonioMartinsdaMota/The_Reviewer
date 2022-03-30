package academy.mindswap.persistence.repositories;

import academy.mindswap.persistence.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    User findByUsernameAndPassword(String name, String password);

    Optional<User> findByEmailAndPassword(String username, String password);


}
