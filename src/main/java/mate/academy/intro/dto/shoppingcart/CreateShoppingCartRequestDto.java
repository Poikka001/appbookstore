package mate.academy.intro.dto.shoppingcart;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateShoppingCartRequestDto {
    @NotNull
    private String name;
    @NotNull
    private String description;
}
