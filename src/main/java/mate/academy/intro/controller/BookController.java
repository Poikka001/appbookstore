package mate.academy.intro.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import mate.academy.intro.dto.book.BookDto;
import mate.academy.intro.dto.book.CreateBookRequestDto;
import mate.academy.intro.service.BookService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PutMapping;

@Tag(name = "Book management", description = "Endpoints for managing books")
@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @GetMapping
    @Operation(description = "Get all books")
    @ApiResponse(responseCode = "200", description = "All books")
    public List<BookDto> getAll(Pageable pageable) {
        return bookService.findAll(pageable);
    }

    @GetMapping("/{bookId}")
    @Operation(description = "Get book by Id")
    @ApiResponse(responseCode = "200", description = "Book by Id")
    public BookDto getBookById(@PathVariable Long bookId) {
        return bookService.getBookById(bookId);
    }

    @GetMapping("/search")
    @Operation(description = "Get all books")
    @ApiResponse(responseCode = "200", description = "All books")
    @Parameters(value = {
            @Parameter(name = "page",
                    description = "Page number to search",
                    schema = @Schema(implementation = Integer.class)),
            @Parameter(name = "size",
                    description = "Number of books per page",
                    schema = @Schema(implementation = Integer.class)),
            @Parameter(name = "sort",
                    description = "Possible fields for sorting: 'title', 'author', 'price'",
                    schema = @Schema(implementation = String.class)),
            @Parameter(name = "'title' - additional properties",
                    description = "Search book by title",
                    schema = @Schema(implementation = String[].class)),
            @Parameter(name = "'author' - additional properties",
                    description = "Search book by author",
                    schema = @Schema(implementation = String[].class))})
    public Page<BookDto> searchBooks(Pageable pageable, @RequestBody Map<String, String[]> params) {
        return bookService.search(pageable, params);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    @Operation(description = "Create new book")
    @ApiResponse(responseCode = "200", description = "Create new book")
    public BookDto createBook(@RequestBody @Valid CreateBookRequestDto bookRequestDto) {
        return bookService.save(bookRequestDto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{bookId}")
    @Operation(description = "Update book")
    @ApiResponse(responseCode = "200", description = "Update book")
    public BookDto updateBook(@PathVariable Long bookId,
                              @RequestBody CreateBookRequestDto bookRequestDto) {
        return bookService.updateBook(bookId, bookRequestDto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(description = "Delete book by Id")
    @ApiResponse(responseCode = "200", description = "Delete book")
    public void deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
    }
}