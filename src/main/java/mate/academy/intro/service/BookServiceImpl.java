package mate.academy.intro.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.intro.dto.book.BookDto;
import mate.academy.intro.dto.book.CreateBookRequestDto;
import mate.academy.intro.exception.EntityNotFoundException;
import mate.academy.intro.mapper.BookMapper;
import mate.academy.intro.model.Book;
import mate.academy.intro.repository.book.BookRepository;
import mate.academy.intro.repository.book.BookSpecificationBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookSpecificationBuilder bookSpecificationBuilder;

    @Override
    public BookDto getBookById(Long bookId) {
        return bookMapper.toDtoBook(bookRepository.findById(bookId).orElseThrow(
                () -> new EntityNotFoundException("Can't get book with id: " + bookId)));
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
    public BookDto save(CreateBookRequestDto bookRequestDto) {
        return bookMapper.toDtoBook(bookRepository.save(bookMapper.toEntity(bookRequestDto)));
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
}
