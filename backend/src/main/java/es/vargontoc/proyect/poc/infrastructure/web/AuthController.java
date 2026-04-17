package es.vargontoc.proyect.poc.infrastructure.web;

import es.vargontoc.proyect.poc.domain.model.User;
import es.vargontoc.proyect.poc.domain.ports.in.AuthenticateUserUseCase;
import es.vargontoc.proyect.poc.domain.ports.in.RegisterUserUseCase;
import es.vargontoc.proyect.poc.dto.AuthResponse;
import es.vargontoc.proyect.poc.dto.LoginRequest;
import es.vargontoc.proyect.poc.dto.RegisterRequest;
import es.vargontoc.proyect.poc.dto.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication", description = "Register and login endpoints")
class AuthController {

    private final RegisterUserUseCase registerUserUseCase;
    private final AuthenticateUserUseCase authenticateUserUseCase;

    AuthController(RegisterUserUseCase registerUserUseCase, AuthenticateUserUseCase authenticateUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
        this.authenticateUserUseCase = authenticateUserUseCase;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Register a new user")
    UserResponse register(@Valid @RequestBody RegisterRequest request) {
        User user = registerUserUseCase.register(request.username(), request.email(), request.password());
        return new UserResponse(user.getId(), user.getUsername(), user.getEmail(), user.getRole());
    }

    @PostMapping("/login")
    @Operation(summary = "Authenticate and receive a JWT token")
    AuthResponse login(@Valid @RequestBody LoginRequest request) {
        String token = authenticateUserUseCase.authenticate(request.username(), request.password());
        return new AuthResponse(token);
    }
}
