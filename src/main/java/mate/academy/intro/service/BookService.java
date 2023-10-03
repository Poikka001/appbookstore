package mate.academy.intro.service;

import java.util.List;
import mate.academy.intro.dto.BookDto;
import mate.academy.intro.dto.CreateRequestBookDto;

public interface BookService {
    BookDto createBook(CreateRequestBookDto bookDto);

    List<BookDto> getAll();

    BookDto getBookById(Long id);
}
