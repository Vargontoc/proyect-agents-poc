package es.vargontoc.proyect.poc.domain.service;

import es.vargontoc.proyect.poc.domain.model.User;
import es.vargontoc.proyect.poc.domain.ports.in.AuthenticateUserUseCase;
import es.vargontoc.proyect.poc.domain.ports.in.RegisterUserUseCase;
import es.vargontoc.proyect.poc.domain.ports.out.UserRepository;
import es.vargontoc.proyect.poc.domain.ports.out.PasswordEncoder;
import es.vargontoc.proyect.poc.domain.ports.out.TokenGenerator;

import java.time.Instant;

public class UserService implements RegisterUserUseCase, AuthenticateUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenGenerator tokenGenerator;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenGenerator tokenGenerator) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenGenerator = tokenGenerator;
    }

    @Override
    public User register(String username, String email, String rawPassword) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username already taken");
        }
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already registered");
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRole("USER");
        user.setCreatedAt(Instant.now());
        user.setUpdatedAt(Instant.now());

        return userRepository.save(user);
    }

    @Override
    public String authenticate(String username, String rawPassword) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        return tokenGenerator.generateToken(username);
    }
}
