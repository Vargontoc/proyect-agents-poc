package es.vargontoc.proyect.poc.domain.ports.in;

import es.vargontoc.proyect.poc.domain.model.User;

public interface RegisterUserUseCase {
    User register(String username, String email, String rawPassword);
}
