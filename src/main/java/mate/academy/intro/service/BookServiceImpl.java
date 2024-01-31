package mate.academy.intro.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mate.academy.intro.dto.book.BookDto;
import mate.academy.intro.dto.book.CreateBookRequestDto;
import mate.academy.intro.exception.EntityNotFoundException;
import mate.academy.intro.mapper.BookMapper;
import mate.academy.intro.model.Book;
import mate.academy.intro.model.Category;
import mate.academy.intro.repository.book.BookRepository;
import mate.academy.intro.repository.book.BookSpecificationBuilder;
import mate.academy.intro.repository.category.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookSpecificationBuilder bookSpecificationBuilder;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public BookDto save(CreateBookRequestDto bookRequestDto) {
        List<Category> categories = getCategories(bookRequestDto);
        Book book = bookMapper.toEntity(bookRequestDto);
        book.setCategories(categories);
        return bookMapper.toDtoBook(bookRepository.save(book));
    }

    @Override
    public BookDto getBookById(Long bookId) {
        return bookMapper.toDtoBook(bookRepository.findById(bookId).orElseThrow(
                () -> new EntityNotFoundException("Can't get book with id: " + bookId)));
    }

    @Override
    public List<BookDto> findAllByPriceInService(BigDecimal fromPrice, BigDecimal toPrice, Pageable pageable) {
        List<Book> books = bookRepository.findAllByPriceBetween(fromPrice, toPrice, pageable);
        return books.stream()
                .map(bookMapper::toDtoBook)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDto> findAllByCategoryId(Long categoryId) {
        List<Book> books = bookRepository.findAllByCategoriesId(categoryId);
        return books.stream()
                .map(bookMapper::toDtoBook)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDto> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).stream()
                .map(bookMapper::toDtoBook)
                .collect(Collectors.toList());
    }

    @Override
    public BookDto updateBook(Long bookId, CreateBookRequestDto bookRequestDto) {
        if (!bookRepository.existsById(bookId)) {
            throw new EntityNotFoundException("Can't found book with id: " + bookId);
        }
        Book book = bookMapper.toEntity(bookRequestDto);
        book.setId(bookId);
        return bookMapper.toDtoBook(bookRepository.save(book));
    }

    @Override
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    public Page<BookDto> search(Pageable pageable, Map<String, String[]> params) {
        return bookRepository.findAll(bookSpecificationBuilder.build(params), pageable)
                .map(bookMapper::toDtoBook);
    }

    private List<Category> getCategories(CreateBookRequestDto createBookRequestDto) {
        return createBookRequestDto.getCategoryIds().stream()
                .map(categoryRepository::getReferenceById)
                .collect(Collectors.toList());
    }
}
