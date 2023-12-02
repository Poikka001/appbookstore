package mate.academy.intro.repository.role;

import mate.academy.intro.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    boolean existsByRoleName(Role.RoleName roleName);
    Role findRoleByRoleName(Role.RoleName user);
}
