package mate.academy.intro.service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import mate.academy.intro.dto.BookDto;
import mate.academy.intro.dto.CreateRequestBookDto;
import mate.academy.intro.exeption.EntityNotFoundException;
import mate.academy.intro.mapper.BookMapper;
import mate.academy.intro.model.Book;
import mate.academy.intro.repository.BookRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    @Override
    public BookDto createBook(CreateRequestBookDto bookDto) {
        Book book = bookMapper.toDto(bookDto);
        return bookMapper.toDto(bookRepository.createBook(book));
    }

    @Override
    public List<BookDto> getAll() {
        return bookRepository.getAll().stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto getBookById(Long id) {
        Book employee = bookRepository.getBookById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find employee by id " + id)
        );
        return bookMapper.toDto(employee);
    }
}
