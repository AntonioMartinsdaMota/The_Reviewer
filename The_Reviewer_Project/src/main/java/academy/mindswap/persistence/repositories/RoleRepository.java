package academy.mindswap.persistence.repositories;

import academy.mindswap.persistence.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(String owner);
}
