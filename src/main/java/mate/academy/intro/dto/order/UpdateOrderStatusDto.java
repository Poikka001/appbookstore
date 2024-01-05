package mate.academy.intro.dto.order;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import mate.academy.intro.model.Status;

@Data
public class UpdateOrderStatusDto {
    @NotNull
    private Status status;
}
