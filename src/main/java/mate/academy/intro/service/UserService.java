package mate.academy.intro.service;

import mate.academy.intro.dto.user.UserRegistrationRequestDto;
import mate.academy.intro.dto.user.UserResponseDto;
import mate.academy.intro.exception.RegistrationException;

public interface UserService {
    UserResponseDto registerUser(UserRegistrationRequestDto requestDto)
            throws RegistrationException;

    UserResponseDto registerAdmin(UserRegistrationRequestDto requestDto)
            throws RegistrationException;
}
