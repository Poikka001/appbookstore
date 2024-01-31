package mate.academy.intro.controller.book;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import mate.academy.intro.dto.book.BookDto;
import mate.academy.intro.dto.book.CreateBookRequestDto;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.shaded.org.apache.commons.lang3.builder.EqualsBuilder;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerTest {
    protected static MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    static void beforeAll(
            @Autowired DataSource dataSource,
            @Autowired WebApplicationContext applicationContext
    ) throws SQLException {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(applicationContext)
                .apply(springSecurity())
                .build();
        teardown(dataSource);

        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(true);
            ScriptUtils.executeSqlScript(
                    connection,
                    new ClassPathResource("database/books/add-three-default-books.sql")
            );
            ScriptUtils.executeSqlScript(connection,
                    new ClassPathResource("database/categories/add-three-default-categories.sql")
            );
            ScriptUtils.executeSqlScript(connection,
                    new ClassPathResource("database/books_categories/add-three-books_categories.sql")
            );
        }
    }

    @AfterAll
    static void afterAll(@Autowired DataSource dataSource) {
        teardown(dataSource);
    }

    @SneakyThrows
    static void teardown(DataSource dataSource) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(true);

            ScriptUtils.executeSqlScript(connection,
                    new ClassPathResource("database/books_categories/delete-three-books_categories.sql")
            );
            ScriptUtils.executeSqlScript(connection,
                    new ClassPathResource("database/categories/delete-three-default-categories.sql")
            );
            ScriptUtils.executeSqlScript(connection,
                    new ClassPathResource("database/books/delete-three-default-books.sql")
            );
        }
    }

    @WithMockUser(username = "adminBob@gmail.com", roles = {"ADMIN"})
    @Test
    @Sql(scripts = "classpath:database/books/delete-one-book.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD
    )
    @DisplayName("Create a new book")
    void createBook_ValidRequestDto_Success() throws Exception {

        // Given
        CreateBookRequestDto requestDto = new CreateBookRequestDto()
                .setTitle("book_title")
                .setAuthor("book_author")
                .setIsbn("book_1234512345")
                .setPrice(BigDecimal.valueOf(10))
                .setDescription("book_description")
                .setCoverImage("book_coverImage")
                .setCategoryIds(List.of(1L));

        BookDto expected = new BookDto()
                .setTitle(requestDto.getTitle())
                .setAuthor(requestDto.getAuthor())
                .setIsbn(requestDto.getIsbn())
                .setPrice(requestDto.getPrice())
                .setDescription(requestDto.getDescription())
                .setCoverImage(requestDto.getCoverImage());

        String jsonRequest = objectMapper.writeValueAsString(requestDto);

        // When
        MvcResult result = mockMvc.perform(
                        post("/books")
                                .content(jsonRequest)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andReturn();
        // Then

        BookDto actual = objectMapper.readValue(result.getResponse().getContentAsString(), BookDto.class);
        Assertions.assertNotNull(actual);
        Assertions.assertNotNull(actual.getId());
        EqualsBuilder.reflectionEquals(expected, actual, "id");
    }

    @WithMockUser(username = "adminBob@gmail.com", roles = {"ADMIN"})
    @Test
    @DisplayName("Get all books")
    void getAll_GivenBooksInCategory_ShouldReturnAllBooks() throws Exception {

        // Given
        List<BookDto> expected = new ArrayList<>();
        expected.add(new BookDto().setId(101L)
                .setTitle("title_default001")
                .setAuthor("author_default001")
                .setIsbn("isbn_default001")
                .setPrice(BigDecimal.valueOf(10))
                .setDescription("description_default001")
                .setCoverImage("default_cover_image001"));

        expected.add(new BookDto().setId(102L)
                .setTitle("title_default002")
                .setAuthor("author_default002")
                .setIsbn("isbn_default002")
                .setPrice(BigDecimal.valueOf(11))
                .setDescription("description_default002")
                .setCoverImage("default_cover_image002"));

        expected.add(new BookDto().setId(103L)
                .setTitle("title_default003")
                .setAuthor("author_default003")
                .setIsbn("isbn_default003")
                .setPrice(BigDecimal.valueOf(12))
                .setDescription("description_default003")
                .setCoverImage("default_cover_image003"));

        // When
        MvcResult result = mockMvc.perform(get("/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Then
        BookDto[] actual = objectMapper.readValue(result.getResponse().getContentAsByteArray(), BookDto[].class);
        Assertions.assertEquals(6, actual.length);

        // There is an 'if' check here because I already have 3 books in my database
        if (actual.length >= 3) {
            BookDto[] actualReform = new BookDto[actual.length - 3];
            System.arraycopy(actual, 3, actualReform, 0, actualReform.length);
            Assertions.assertEquals(expected, Arrays.stream(actualReform).toList());
        }
    }
}
