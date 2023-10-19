package mate.academy.intro.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Random;
import java.util.stream.Collectors;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateRequestBookDto {
    @NotNull
    private String title;
    @NotNull
    private String author;

    private String isbn = "" + new Random().
            ints(0, 10).
            limit(13).
            mapToObj(Integer::toString).
            collect(Collectors.joining());

    @NotNull
    @Min(0)
    private BigDecimal price;

    private String description;

    private String coverImage;
}
