package mate.academy.intro.service;

import java.util.List;
import mate.academy.intro.dto.book.BookDto;
import mate.academy.intro.dto.book.BookSearchParameters;
import mate.academy.intro.dto.book.CreateRequestBookDto;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookDto save(CreateRequestBookDto requestDto);

    List<BookDto> findAll(Pageable pageable);

    BookDto findById(Long id);

    void deleteById(Long id);

    List<BookDto> search(BookSearchParameters params);
}
