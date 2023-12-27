package mate.academy.intro.service;

import mate.academy.intro.dto.category.CategoryDto;
import mate.academy.intro.dto.category.CreateCategoryRequestDto;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAll(Pageable pageable);

    CategoryDto save(CreateCategoryRequestDto categoryRequestDto);

    CategoryDto getCategoryById(Long categoryId);

    CategoryDto updateCategory(Long categoryId, CreateCategoryRequestDto categoryRequestDto);

    void deleteCategory(Long categoryId);
}
