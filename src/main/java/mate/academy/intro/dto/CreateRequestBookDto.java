package mate.academy.intro.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Random;
import java.util.stream.Collectors;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateRequestBookDto {
    private String title;

    private String author;

    private String isbn = "" + new Random().
            ints(0, 10).
            limit(13).
            mapToObj(Integer::toString).
            collect(Collectors.joining());

    private BigDecimal price;

    private String description;

    private String coverImage;
}
