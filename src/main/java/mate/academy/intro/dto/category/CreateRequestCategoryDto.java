package mate.academy.intro.dto.category;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateRequestCategoryDto {
    @NotNull
    private String name;
    @NotNull
    private String description;
}
