package es.vargontoc.proyect.poc.domain.ports.out;

public interface TokenGenerator {
    String generateToken(String username);
}
