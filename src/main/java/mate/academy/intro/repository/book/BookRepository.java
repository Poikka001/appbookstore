package mate.academy.intro.repository.book;

import mate.academy.intro.model.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book>  {
    List<Book> findAllByCategoriesId(Long categoryId);

    @Query("from Book b where b.price between ?1 and ?2")
    List<Book> findAllByPriceBetween(BigDecimal fromPrice, BigDecimal toPrice, Pageable pageable);
}
