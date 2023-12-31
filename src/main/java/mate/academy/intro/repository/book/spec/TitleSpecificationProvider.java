package mate.academy.intro.repository.book.spec;

import mate.academy.intro.model.Book;
import mate.academy.intro.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Component
public class TitleSpecificationProvider implements SpecificationProvider<Book> {
    @Override
    public String getKey() {
        return "title";
    }

    public Specification<Book> getSpecification(String[] params) {
        return ((root, query, criteriaBuilder) -> root
                .get("title")
                .in(Arrays.stream(params).toArray()));
    }
}
