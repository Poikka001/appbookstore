package mate.academy.intro.service;

import mate.academy.intro.model.Role;

public interface RoleService {
    Role save(Role role);

    boolean exists(Role.RoleName roleName);
}
