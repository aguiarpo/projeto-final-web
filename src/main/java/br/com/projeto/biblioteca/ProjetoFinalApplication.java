package br.com.projeto.biblioteca;

import br.com.projeto.biblioteca.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class ProjetoFinalApplication implements CommandLineRunner {

    private final UserRepository userRepository;

    public ProjetoFinalApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProjetoFinalApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
