package mate.academy.intro.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import mate.academy.intro.dto.fieldMatch.FieldMatch;
import org.hibernate.validator.constraints.Length;

@Data
@FieldMatch.List({
        @FieldMatch(first = "password", second = "repeatPassword",
                message = "The password fields must match")
})
public class UserRegistrationRequestDto {
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Length(min = 8, max = 35)
    private String password;

    @NotBlank(message = "Repeat password cannot be blank")
    @Length(min = 8, max = 35)
    private String repeatPassword;

    @NotBlank(message = "First name cannot be blank")
    @Length(min = 1, max = 35)
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Length(min = 1, max = 35)
    private String lastName;

    @NotBlank
    private String shippingAddress;
}
