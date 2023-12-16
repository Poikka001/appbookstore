package mate.academy.intro.repository.Category;

import mate.academy.intro.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CategoryRepository extends JpaRepository<Category,
        Long>, JpaSpecificationExecutor<Category> {
}