package es.vargontoc.proyect.poc.application;

import es.vargontoc.proyect.poc.domain.ports.in.AuthenticateUserUseCase;
import es.vargontoc.proyect.poc.domain.ports.in.RegisterUserUseCase;
import es.vargontoc.proyect.poc.domain.ports.out.PasswordEncoder;
import es.vargontoc.proyect.poc.domain.ports.out.TokenGenerator;
import es.vargontoc.proyect.poc.domain.ports.out.UserRepository;
import es.vargontoc.proyect.poc.domain.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserServiceConfig {

    @Bean
    RegisterUserUseCase registerUserUseCase(UserRepository userRepository,
                                            PasswordEncoder passwordEncoder,
                                            TokenGenerator tokenGenerator) {
        return new UserService(userRepository, passwordEncoder, tokenGenerator);
    }

    @Bean
    AuthenticateUserUseCase authenticateUserUseCase(UserRepository userRepository,
                                                     PasswordEncoder passwordEncoder,
                                                     TokenGenerator tokenGenerator) {
        return new UserService(userRepository, passwordEncoder, tokenGenerator);
    }
}
