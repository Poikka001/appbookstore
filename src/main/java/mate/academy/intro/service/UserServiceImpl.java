package mate.academy.intro.service;

import lombok.RequiredArgsConstructor;
import mate.academy.intro.dto.user.UserRegistrationRequestDto;
import mate.academy.intro.dto.user.UserResponseDto;
import mate.academy.intro.exception.RegistrationException;
import mate.academy.intro.mapper.UserMapper;
import mate.academy.intro.model.Role;
import mate.academy.intro.model.User;
import mate.academy.intro.repository.role.RoleRepository;
import mate.academy.intro.repository.user.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (requestDto.getEmail().startsWith("admin")) {
            User admin = userToMapper(requestDto);
            admin.setRoles(Set.of(roleRepository.findRoleByRoleName(Role.RoleName.ADMIN)));
            return userMapper.toUserResponseDto(userRepository.save(admin));
        }
        User user = userToMapper(requestDto);
        user.setRoles(Set.of(roleRepository.findRoleByRoleName(Role.RoleName.USER)));
        return userMapper.toUserResponseDto(userRepository.save(user));
    }

    private User userToMapper(UserRegistrationRequestDto requestDto) throws RegistrationException {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("Unable to complete registration");
        }
        return userMapper.toModel(requestDto);
    }
}
