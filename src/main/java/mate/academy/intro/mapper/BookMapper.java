package mate.academy.intro.mapper;

import mate.academy.intro.config.MapperConfig;
import mate.academy.intro.dto.book.BookDto;
import mate.academy.intro.dto.book.BookDtoWithoutCategoryIds;
import mate.academy.intro.dto.book.CreateBookRequestDto;
import mate.academy.intro.model.Book;
import mate.academy.intro.util.MyBatisUtil;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.SqlSession;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.repository.query.Param;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toDtoBook(Book model);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    Book toEntity(CreateBookRequestDto bookDto);

    @Select("SELECT * FROM books WHERE id = #{id}")
    @Named("bookFromId")
    default Book bookFromId(@Param("id") Long id) {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession()) {
            return sqlSession.selectOne("path.to.your.BookMapper.bookFromId", id);
        }
    }

    BookDtoWithoutCategoryIds toDtoWithoutCategories (Book book);
}
