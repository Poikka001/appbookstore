package mate.academy.intro.service;

import mate.academy.intro.dto.category.CategoryDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAll(Pageable pageable);
}
