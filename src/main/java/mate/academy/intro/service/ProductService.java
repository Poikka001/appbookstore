package mate.academy.intro.service;

import java.util.List;

public interface ProductService {

    User save(User user);

    List<User> findAll();
}
