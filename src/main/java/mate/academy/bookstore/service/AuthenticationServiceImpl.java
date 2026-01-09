package mate.academy.bookstore.service;

import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.UserLoginRequestDto;
import mate.academy.bookstore.dto.UserLoginResponseDto;
import mate.academy.bookstore.dto.UserRegistrationRequestDto;
import mate.academy.bookstore.dto.UserResponseDto;
import mate.academy.bookstore.exception.RegistrationException;
import mate.academy.bookstore.mapper.UserMapper;
import mate.academy.bookstore.model.Role;
import mate.academy.bookstore.model.RoleName;
import mate.academy.bookstore.model.User;
import mate.academy.bookstore.repository.RoleRepository;
import mate.academy.bookstore.repository.UserRepository;
import mate.academy.bookstore.security.JwtUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final JwtUtil jwtUtil;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto request)
            throws RegistrationException {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RegistrationException(
                    "User with email " + request.getEmail() + " already exists"
            );
        }

        User user = userMapper.toModel(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() ->
                        new RegistrationException("Role" + RoleName.ROLE_USER + "not found"));

        user.getRoles().add(userRole);

        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserLoginResponseDto login(UserLoginRequestDto request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Bad credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Bad credentials");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getAuthorities());
        return new UserLoginResponseDto(token);
    }
}
