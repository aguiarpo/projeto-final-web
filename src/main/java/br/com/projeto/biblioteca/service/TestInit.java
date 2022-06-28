package br.com.projeto.biblioteca.service;

import br.com.projeto.biblioteca.models.Book;
import br.com.projeto.biblioteca.models.Genre;
import br.com.projeto.biblioteca.models.User;
import br.com.projeto.biblioteca.models.enums.TypeUsers;
import br.com.projeto.biblioteca.repository.BookRepository;
import br.com.projeto.biblioteca.repository.GenreRepository;
import br.com.projeto.biblioteca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Profile("dev")
public class TestInit {
    private final UserRepository userRepository;
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;

    private List<String> urlList = new ArrayList<>();

    @Autowired
    public TestInit(UserRepository userRepository,
                    GenreRepository genreRepository,
                    BookRepository bookRepository){
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void createTest() {
        if (userRepository.findByEmail("eduardo.aguiarpo@gmail.com") == null) {
            User admin = new User();
            admin.setEmail("eduardo.aguiarpo@gmail.com");
            admin.setPassword("12345678");
            admin.setName("Eduardo Aguiar");
            admin.setType(TypeUsers.ADMIN);
            userRepository.save(admin);
        }


        urlList.add("https://images-na.ssl-images-amazon.com/images/I/41l0ZeWjsgL._SY344_BO1,204,203,200_QL70_ML2_.jpg");
        urlList.add("https://images-na.ssl-images-amazon.com/images/I/51DguzEMjeS._SX331_BO1,204,203,200_.jpg");
        urlList.add("https://images-na.ssl-images-amazon.com/images/I/41ChLRWRMCS._SX342_BO1,204,203,200_.jpg");
        urlList.add("https://images-na.ssl-images-amazon.com/images/I/51wx3A+5s6L._SX347_BO1,204,203,200_.jpg");
        urlList.add("https://images-na.ssl-images-amazon.com/images/I/51kntc-FBPL._SX432_BO1,204,203,200_.jpg");
        urlList.add("https://images-na.ssl-images-amazon.com/images/I/51qO3gsELvL._SX321_BO1,204,203,200_.jpg");

        List<Genre> genres = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            if(genreRepository.findByName("Genre" + i) == null) {
                Genre genre = new Genre();
                genre.setName("Genre" + i);
                Genre saved = genreRepository.save(genre);
                genres.add(saved);
            }
                for (int x = 0; x < 6; x++) {
                    if(bookRepository.findByBarcode("32131" + x) == null){
                        Book book = new Book();
                        book.setYear((short) 1000);
                        book.setEdition((byte) x);
                        book.setAuthor("Autor" + x);
                        book.setBarcode("32131" + x);
                        book.setName("Nome" + x);
                        // book.setQuant(3);
                        book.setUrl(urlList.get(x));
                        book.setGenres(genres);
                        bookRepository.save(book);
                    }
                }
            }
        }

}