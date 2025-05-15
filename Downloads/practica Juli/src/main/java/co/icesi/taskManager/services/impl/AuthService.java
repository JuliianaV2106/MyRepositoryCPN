package co.icesi.taskManager.services.impl;

import co.icesi.taskManager.dtos.UserDTO;
import co.icesi.taskManager.model.User;
import co.icesi.taskManager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder encoder;

    public User login(UserDTO userDto) {
        User userOpt = userRepository.findByUsername(userDto.getUsername());

        if (encoder.matches(userDto.getPassword(), userOpt.getPassword())) {
            return userOpt;
        }

        throw new IllegalArgumentException("Invalid credentials");
    }
}
