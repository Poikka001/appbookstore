package mate.academy.intro.repository.book;

import lombok.RequiredArgsConstructor;
import mate.academy.intro.model.Book;
import mate.academy.intro.repository.SpecificationBuilder;
import mate.academy.intro.repository.SpecificationProviderManager;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Map;

@RequiredArgsConstructor
@Component
public class BookSpecificationBuilder implements SpecificationBuilder<Book> {
    private final SpecificationProviderManager<Book> bookSpecificationProviderManager;
    @Override
    public Specification<Book> build(Map<String, String[]> params) {
        Specification<Book> spec = Specification.where(null);
        for (String key: params.keySet()) {
            if (params.get(key).length > 0) {
                spec = spec.and(
                        bookSpecificationProviderManager.getSpecificationProvider(key)
                                .getSpecification(params.get(key)));
            }
        }
        return spec;
    }
}
