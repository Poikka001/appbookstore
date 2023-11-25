package mate.academy.intro.repository.book;

import lombok.RequiredArgsConstructor;
import mate.academy.intro.dto.book.BookSearchParameters;
import mate.academy.intro.model.Book;
import mate.academy.intro.repository.SpecificationBuilder;
import mate.academy.intro.repository.SpecificationProviderManager;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookSpecificationBuilder implements SpecificationBuilder<Book> {
    private final SpecificationProviderManager<Book> phoneSpecificationProviderManager;

    @Override
    public Specification<Book> build(BookSearchParameters searchParameters) {
        Specification<Book> spec = Specification.where(null);

        if (searchParameters.titles() != null && searchParameters.titles().length > 0) {
            spec = spec.and(phoneSpecificationProviderManager.getSpecificationProvider("title")
                    .getSpecification(searchParameters.titles()));
        }
        if (searchParameters.authors() != null && searchParameters.authors().length > 0) {
            spec = spec.and(phoneSpecificationProviderManager.getSpecificationProvider("author")
                    .getSpecification(searchParameters.authors()));
        }
        return spec;
    }
}
