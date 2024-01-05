package mate.academy.intro.dto.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateOrderDto {
    @NotBlank
    private String shippingAddress;
}
