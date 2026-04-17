package es.vargontoc.proyect.poc.domain.ports.in;

public interface AuthenticateUserUseCase {
    String authenticate(String username, String password);
}
