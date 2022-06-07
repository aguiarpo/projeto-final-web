package br.com.projeto.biblioteca;

import br.com.projeto.biblioteca.models.Book;
import br.com.projeto.biblioteca.models.Loan;
import br.com.projeto.biblioteca.models.User;
import br.com.projeto.biblioteca.repository.BookRepository;
import br.com.projeto.biblioteca.repository.GenreRepository;
import br.com.projeto.biblioteca.repository.LoanRepository;
import br.com.projeto.biblioteca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootApplication
public class ProjetoFinalApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ProjetoFinalApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
