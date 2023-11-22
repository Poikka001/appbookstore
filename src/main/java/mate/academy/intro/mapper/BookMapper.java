package mate.academy.intro.mapper;

import mate.academy.intro.config.MapperConfig;
import mate.academy.intro.dto.book.BookDto;
import mate.academy.intro.dto.book.CreateRequestBookDto;
import mate.academy.intro.model.Book;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);
    Book toDto(CreateRequestBookDto requestDto);
}
//{
//  "email": "john.doe@example.com",
//  "password": "securePassword123",
//  "repeatPassword": "securePassword123",
//  "firstName": "John",
//  "lastName": "Doe",
//  "shippingAddress": "123 Main St, City, Country"
//}
