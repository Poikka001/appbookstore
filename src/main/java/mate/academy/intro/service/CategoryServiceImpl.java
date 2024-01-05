package mate.academy.intro.service;

import lombok.RequiredArgsConstructor;
import mate.academy.intro.dto.category.CategoryDto;
import mate.academy.intro.dto.category.CreateCategoryRequestDto;
import mate.academy.intro.exception.EntityNotFoundException;
import mate.academy.intro.mapper.CategoryMapper;
import mate.academy.intro.model.Category;
import mate.academy.intro.repository.category.CategoryRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryDto> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable).stream()
                .map(categoryMapper::toDoCategory)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto save(CreateCategoryRequestDto categoryRequestDto) {
        return categoryMapper.toDoCategory(categoryRepository
                .save(categoryMapper.toEntity(categoryRequestDto)));
    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) {
        return categoryMapper.toDoCategory(categoryRepository.findById(categoryId).orElseThrow(
                () -> new EntityNotFoundException("Can't get category with id: " + categoryId)));
    }

    @Override
    public CategoryDto updateCategory(Long categoryId, CreateCategoryRequestDto categoryRequestDto) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new EntityNotFoundException("Can't found category with id: " + categoryId);
        }
        Category category = categoryMapper.toEntity(categoryRequestDto);
        category.setId(categoryId);
        return categoryMapper.toDoCategory(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
