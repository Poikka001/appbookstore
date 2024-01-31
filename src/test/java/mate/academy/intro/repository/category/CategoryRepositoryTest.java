package mate.academy.intro.repository.category;

import lombok.SneakyThrows;
import mate.academy.intro.model.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;


    @BeforeAll
    static void beforeAll(@Autowired DataSource dataSource) {
        tearDown(dataSource);
    }

    @BeforeEach
    void beforeEach(@Autowired DataSource dataSource) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(true);
            ScriptUtils.executeSqlScript(connection,
                    new ClassPathResource("database/categories/add-three-default-categories.sql"));
        }
    }

    @AfterEach
    void afterEach(@Autowired DataSource dataSource) {
        tearDown(dataSource);
    }

    @SneakyThrows
    static void tearDown(DataSource dataSource) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(true);
            ScriptUtils.executeSqlScript(connection,
                    new ClassPathResource("database/categories/delete-three-default-categories.sql"));
        }
    }

    @Test
    @DisplayName("Find category where id 1,2 and 3")
    void getBookByIdTestAssertSuccess() {
        Optional<Category> category1 = categoryRepository.findById(111L);
        Optional<Category> category2 = categoryRepository.findById(222L);
        Optional<Category> category3 = categoryRepository.findById(333L);
        assertEquals(111L,category1.get().getId());
        assertEquals(222L,category2.get().getId());
        assertEquals(333L,category3.get().getId());
        assertEquals("category001",category1.get().getName());
        assertEquals("category002",category2.get().getName());
        assertEquals("category003",category3.get().getName());
    }
}
