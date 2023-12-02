package mate.academy.intro.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import mate.academy.intro.dto.user.UserRegistrationRequestDto;
import mate.academy.intro.exception.RegistrationException;
import mate.academy.intro.service.UserService;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class MembersConfig {
    private final UserService userService;

    @PostConstruct
    public void init() throws RegistrationException {
        UserRegistrationRequestDto bobAdmin = new UserRegistrationRequestDto();
        bobAdmin.setEmail("bobAdmin@gmail.com");
        bobAdmin.setPassword("12345678");
        bobAdmin.setRepeatPassword("12345678");
        bobAdmin.setFirstName("Bob");
        bobAdmin.setLastName("BobSon");
        bobAdmin.setShippingAddress("baker street 22");
        userService.registerAdmin(bobAdmin);

        UserRegistrationRequestDto aliceUser = new UserRegistrationRequestDto();
        aliceUser.setEmail("aliceUser@gmail.com");
        aliceUser.setPassword("12345678");
        aliceUser.setRepeatPassword("12345678");
        aliceUser.setFirstName("alice");
        aliceUser.setLastName("BobSon");
        aliceUser.setShippingAddress("baker street 23");
        userService.registerUser(aliceUser);

        UserRegistrationRequestDto johnUser = new UserRegistrationRequestDto();
        johnUser.setEmail("johnUser@gmail.com");
        johnUser.setPassword("12345678");
        johnUser.setRepeatPassword("12345678");
        johnUser.setFirstName("john");
        johnUser.setLastName("johnSon");
        johnUser.setShippingAddress("baker street 24");
        userService.registerUser(johnUser);
    }
}
