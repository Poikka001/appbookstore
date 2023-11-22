package mate.academy.intro.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserRegistrationRequestDto {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Length(min = 8, max = 35)
    private String password;

    @NotBlank
    @Length(min = 8, max = 35)
    private String repeatPassword;

    @NotBlank
    @Length(min = 1, max = 35)
    private String firstName;

    @NotBlank
    @Length(min = 1, max = 35)
    private String lastName;

    @NotBlank
    private String shippingAddress;
}
