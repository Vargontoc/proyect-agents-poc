package es.vargontoc.proyect.poc.infrastructure.security;

import es.vargontoc.proyect.poc.domain.ports.out.TokenGenerator;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class TokenGeneratorAdapter implements TokenGenerator {

    private final JwtService jwtService;

    public TokenGeneratorAdapter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public String generateToken(String username) {
        return jwtService.generateToken(
                new User(username, "", Collections.emptyList()));
    }
}
