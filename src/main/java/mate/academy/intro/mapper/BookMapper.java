package mate.academy.intro.mapper;

import mate.academy.intro.config.MapperConfig;
import mate.academy.intro.dto.BookDto;
import mate.academy.intro.dto.CreateRequestBookDto;
import mate.academy.intro.model.Book;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

    Book toDto(CreateRequestBookDto requestDto);
}
