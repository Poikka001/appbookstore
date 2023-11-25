package mate.academy.intro.dto.user;

import lombok.Data;

@Data
public class UserResponseDto {
    private long id;
    private String email;
    private String firstName;
    private String lastName;
    private String shippingAddress;
}
