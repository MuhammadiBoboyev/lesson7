package uz.pdp.lesson7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson7.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    boolean existsByRoleName(String roleName);

    Optional<Role> findByRoleName(String name);

}
