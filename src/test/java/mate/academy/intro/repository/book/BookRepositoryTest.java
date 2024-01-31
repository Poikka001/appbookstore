package mate.academy.intro.repository.book;

import mate.academy.intro.model.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("""
            Find all books by price when price is less than 100
                  """)
    @Sql(scripts = {
            "classpath:database/books/add-kobzar-book-to-books-table.sql"
    }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {
            "classpath:database/books/remove-kobzar-book-to-books-table.sql"
    }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void findAllByPriceBetween_PriceLessThan100_ReturnsEmptyList() {
        List<Book> actual = bookRepository.findAllByPriceBetween(
                BigDecimal.ZERO, BigDecimal.valueOf(100), PageRequest.of(0, 10)
        );

        assertEquals(4, actual.size());
        assertEquals("Kobzar", actual.get(3).getAuthor());
    }

    @Test
    @DisplayName("Find book where id 1 and 2")
    void getBookByIdTestAssertSuccess() {
        Optional<Book> book1 = bookRepository.findById(1L);
        Optional<Book> book2 = bookRepository.findById(2L);
        assertEquals(1L,book1.get().getId());
        assertEquals(2L,book2.get().getId());
        assertEquals("Lord of the Rings",book1.get().getTitle());
        assertEquals("Game of Thrones",book2.get().getTitle());
    }
}
