package br.com.projeto.biblioteca.controllers;

import br.com.projeto.biblioteca.models.Book;
import br.com.projeto.biblioteca.models.Genre;
import br.com.projeto.biblioteca.models.Loan;
import br.com.projeto.biblioteca.models.User;
import br.com.projeto.biblioteca.service.BookService;
import br.com.projeto.biblioteca.service.LoanService;
import br.com.projeto.biblioteca.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;
    @Autowired
    private LoanService loanService;

    @GetMapping()
    public String home(Model model) {
        List<Genre> genres = bookService.findAllGenre();
        model.addAttribute("genres", genres);
        return "home";
    }

    @GetMapping("books/{id}")
    public String getBooks(Model model, @PathVariable Short id) {
        List<Genre> genres = bookService.findAllGenre();
        Optional<Genre> genre = bookService.findByIdGenre(id);
        List<Genre> genreList = new ArrayList<>();
        genreList.add(genre.get());
        List<Book> books = genre.get().getBooks();
        model.addAttribute("genres", genres);
        model.addAttribute("books", books);
        model.addAttribute("genre", genre.get());
        return "books";
    }

    @GetMapping("books")
    public String getBooksALL(Model model) {
        List<Genre> genres = bookService.findAllGenre();
        List<Book> books = bookService.findAllBook();
        for (Book book: books) {
            int count = loanService.finByCount(book.getId());
            book.setQuant(book.getQuant() - count);
        }
        model.addAttribute("genres", genres);
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("book/{id}")
    public String getBook(Model model, @PathVariable Long id) {
        Optional<Book> books = bookService.findByIdBook(id);
        List<Genre> genres = bookService.findAllGenre();
        model.addAttribute("genres", genres);
        model.addAttribute("book", books.get());
        return "book";
    }

    @GetMapping("book/loan/{id}")
    public String emprestimo(Model model, Principal principal, @PathVariable Long id) {
        Optional<Book> book = bookService.findByIdBook(id);
        List<Genre> genres = bookService.findAllGenre();
        model.addAttribute("genres", genres);
        Loan loan = new Loan();
        loan.setBook(book.get());
        User user = userService.findByEmail(principal.getName());
        loan.setUser(user);
        loanService.addAndEditLoan(loan);
        return "redirect:/books";
    }
}
