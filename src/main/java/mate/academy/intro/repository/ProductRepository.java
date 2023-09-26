package mate.academy.intro.repository;

import java.util.List;

public interface ProductRepository {
    User save(User user);

    List<User> findAll();
}
