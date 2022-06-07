package br.com.projeto.biblioteca;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjetoFinalApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ProjetoFinalApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
