package academy.mindswap.persistence.repositories;

import academy.mindswap.persistence.models.User;

public interface UserRepository {
    User findByUsername(String userName);
}
