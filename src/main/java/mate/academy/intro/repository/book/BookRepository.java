package mate.academy.intro.repository.book;

import mate.academy.intro.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

//public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
//    List<Book> findAllByCategoryId(Long categoryId);
//}


public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book>  {
    List<Book> findAllByCategoriesId(Long categoryId);
}