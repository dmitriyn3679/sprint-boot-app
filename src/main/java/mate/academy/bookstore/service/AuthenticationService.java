package mate.academy.bookstore.service;

import mate.academy.bookstore.dto.UserRegistrationRequestDto;
import mate.academy.bookstore.dto.UserResponseDto;
import mate.academy.bookstore.exception.RegistrationException;

public interface AuthenticationService {
    UserResponseDto register(UserRegistrationRequestDto request) throws RegistrationException;
}
