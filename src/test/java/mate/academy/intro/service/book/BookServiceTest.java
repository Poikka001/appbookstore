package mate.academy.intro.service.book;

import mate.academy.intro.dto.book.BookDto;
import mate.academy.intro.dto.book.CreateBookRequestDto;
import mate.academy.intro.mapper.BookMapper;
import mate.academy.intro.model.Book;
import mate.academy.intro.model.Category;
import mate.academy.intro.repository.book.BookRepository;
import mate.academy.intro.repository.category.CategoryRepository;
import mate.academy.intro.service.BookServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @InjectMocks
    private BookServiceImpl bookService;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private BookMapper bookMapper;
    @Mock
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("Verify save() method works")
    public void save_ValidCreateBookRequestDto_ReturnsBookDto() {

        // Given
        CreateBookRequestDto requestDto = new CreateBookRequestDto();
        requestDto.setTitle("Book Test");
        requestDto.setAuthor("Bohun");
        requestDto.setIsbn("1234512345");
        requestDto.setPrice(BigDecimal.valueOf(10));
        requestDto.setDescription("yo yo yo");
        requestDto.setCoverImage("https://cdn8.openculture.com");
        requestDto.setCategoryIds(List.of(1L));

        Book book = new Book();
        book.setTitle("Book Test");
        book.setAuthor("Bohun");
        book.setIsbn("1234512345");
        book.setPrice(BigDecimal.valueOf(10));
        book.setDescription("yo yo yo");
        book.setCoverImage("https://cdn8.openculture.com");

        BookDto bookDto = new BookDto();
        bookDto.setId(6L);
        bookDto.setTitle("Book Test");
        bookDto.setAuthor("Bohun");
        bookDto.setIsbn("1234512345");
        bookDto.setPrice(BigDecimal.valueOf(10));
        bookDto.setDescription("yo yo yo");
        bookDto.setCoverImage("https://cdn8.openculture.com");
        bookDto.setCategoryIds(List.of(1L));

        when(bookMapper.toEntity(requestDto)).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(book);
        when(bookMapper.toDtoBook(book)).thenReturn(bookDto);
        when(categoryRepository.getReferenceById(anyLong())).thenReturn(new Category());

        // When
        BookDto savedBookDto = bookService.save(requestDto);

        // Then
        assertThat(savedBookDto).isEqualTo(bookDto);
        verify(bookRepository, times(1)).save(book);
        verifyNoMoreInteractions(bookRepository, bookMapper);
    }

    @Test
    @DisplayName("Verify findAll() method works")
    public void findAll_ValidPageable_ReturnsAllBooks() {

        Book book = new Book();
        book.setTitle("Book Test");
        book.setAuthor("Bohun");
        book.setIsbn("1234512345");
        book.setPrice(BigDecimal.valueOf(10));
        book.setDescription("yo yo yo");
        book.setCoverImage("https://cdn8.openculture.com");

        BookDto bookDto = new BookDto();
        bookDto.setId(6L);
        bookDto.setTitle("Book Test");
        bookDto.setAuthor("Bohun");
        bookDto.setIsbn("1234512345");
        bookDto.setPrice(BigDecimal.valueOf(10));
        bookDto.setDescription("yo yo yo");
        bookDto.setCoverImage("https://cdn8.openculture.com");

        Pageable pageable = PageRequest.of(0, 10);
        List<Book> books = List.of(book);
        Page<Book> bookPage = new PageImpl<>(books, pageable, books.size());

        when(bookRepository.findAll(pageable)).thenReturn(bookPage);
        when(bookMapper.toDtoBook(book)).thenReturn(bookDto);

        // When
        List<BookDto> bookDtos = bookService.findAll(pageable);

        // Then
        assertThat(bookDtos).hasSize(1);
        assertThat(bookDtos.get(0)).isEqualTo(bookDto);

        verify(bookRepository, times(1)).findAll(pageable);
        verify(bookMapper, times(1)).toDtoBook(book);
        verifyNoMoreInteractions(bookRepository, bookMapper);
    }

    @Test
    @DisplayName("Verify findAll() method with price range works")
    public void findAll_ValidPriceRangeAndPageable_ReturnsAllBooks() {

        BigDecimal fromPrice = BigDecimal.valueOf(50);
        BigDecimal toPrice = BigDecimal.valueOf(200);

        Book book = new Book();
        book.setTitle("Book Test");
        book.setAuthor("Bohun");
        book.setIsbn("1234512345");
        book.setPrice(BigDecimal.valueOf(100));
        book.setDescription("yo yo yo");
        book.setCoverImage("https://cdn8.openculture.com");

        BookDto bookDto = new BookDto();
        bookDto.setId(6L);
        bookDto.setTitle("Book Test");
        bookDto.setAuthor("Bohun");
        bookDto.setIsbn("1234512345");
        bookDto.setPrice(BigDecimal.valueOf(100));
        bookDto.setDescription("yo yo yo");
        bookDto.setCoverImage("https://cdn8.openculture.com");

        Pageable pageable = PageRequest.of(0, 10);
        List<Book> books = Collections.singletonList(book);

        when(bookRepository.findAllByPriceBetween(fromPrice, toPrice, pageable)).thenReturn(books);
        when(bookMapper.toDtoBook(book)).thenReturn(bookDto);

        // When
        List<BookDto> bookDtos = bookService.findAllByPriceInService(fromPrice, toPrice, pageable);

        assertThat(bookDtos).hasSize(1);
        assertThat(bookDtos.get(0)).isEqualTo(bookDto);
        verify(bookRepository, times(1)).findAllByPriceBetween(fromPrice, toPrice, pageable);
        verify(bookMapper, times(1)).toDtoBook(book);
        verifyNoMoreInteractions(bookRepository, bookMapper);
    }
}
