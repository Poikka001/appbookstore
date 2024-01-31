package mate.academy.intro.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import mate.academy.intro.dto.book.BookDto;
import mate.academy.intro.dto.book.CreateBookRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    List<BookDto> findAllByPriceInService(BigDecimal fromPrice, BigDecimal toPrice, Pageable pageable);

    List<BookDto> findAllByCategoryId(Long categoryId);

    BookDto save(CreateBookRequestDto bookRequestDto);

    List<BookDto> findAll(Pageable pageable);

    BookDto getBookById(Long bookId);

    BookDto updateBook(Long bookId, CreateBookRequestDto bookRequestDto);

    void deleteBook(Long bookId);

    Page<BookDto> search(Pageable pageable, Map<String, String[]> params);
}
