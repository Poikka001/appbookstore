package mate.academy.intro.repository.book;

import lombok.RequiredArgsConstructor;
import mate.academy.intro.exeption.EntityNotFoundException;
import mate.academy.intro.model.Book;
import mate.academy.intro.repository.SpecificationProvider;
import mate.academy.intro.repository.SpecificationProviderManager;
import org.springframework.stereotype.Component;
import java.util.List;

@RequiredArgsConstructor
@Component
public class BookSpecificationProviderManager implements SpecificationProviderManager<Book> {
    private final List<SpecificationProvider<Book>> bookSpecificationProvider;

    @Override
    public SpecificationProvider<Book> getSpecificationProvider(String key) {
        return bookSpecificationProvider.stream()
                .filter(p -> p.getKey().equals(key))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException
                        ("Can't find correct specification provider for key " + key));
    }
}
