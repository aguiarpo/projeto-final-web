package br.com.projeto.biblioteca.controllers;

import br.com.projeto.biblioteca.dto.BookRegister;
import br.com.projeto.biblioteca.dto.GenreRegister;
import br.com.projeto.biblioteca.models.Book;
import br.com.projeto.biblioteca.models.Genre;
import br.com.projeto.biblioteca.service.BookService;
import br.com.projeto.biblioteca.statics.ModalAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("console/book")
    public String getBook(Model model, Pageable pageable) {

        Page<Book> books = bookService.findAllBook(pageable);
        model = ModalAttribute.setAttribute(books, model, "BOOK");
        return "console";
    }

    @GetMapping("console/book/add")
    public String addBook(@ModelAttribute("book") BookRegister book, Model model) {
        List<Genre> genres = bookService.findAllGenre();
        model = ModalAttribute.setAttributeList(genres, model, "ADD-BOOK");
        return "console";
    }

    @PostMapping("console/book/add")
    public String addBookPost(@Valid @ModelAttribute("book") BookRegister book,
                               BindingResult result, Model model) {

        if(result.hasErrors()) {
            List<Genre> genres = bookService.findAllGenre();
            model = ModalAttribute.setAttributeList(genres, model, "ADD-BOOK");
            return "console";
        }
        bookService.addBook(book);
        return "redirect:/admin/console/book";
    }

    @GetMapping("console/book/edit/{id}")
    public String editGenre(@ModelAttribute("book") BookRegister book,
                            @PathVariable Long id, Model model) {
        Optional<Book> bookOptional = bookService.findByIdBook(id);
        List<Genre> genres = bookService.findAllGenre();
        model = ModalAttribute.setAttributeList(genres, model, "EDIT-BOOK");
        book.setId(bookOptional.get().getId());
        book.setName(bookOptional.get().getName());
        book.setEdition(bookOptional.get().getEdition());
        book.setAuthor(bookOptional.get().getAuthor());
        book.setBarcode(bookOptional.get().getBarcode());
        book.setYear(bookOptional.get().getYear());
        book.setQuant(bookOptional.get().getQuant());
        book.setUrl(bookOptional.get().getUrl());
        List<Short> idList = new ArrayList<Short>();
        for (Genre genre: bookOptional.get().getGenres()) {
            idList.add(genre.getId());
        }
        book.setGenres(idList);
        return "console";
    }

    @PostMapping("console/book/edit")
    public String editGenrePost(@Valid @ModelAttribute("book") BookRegister book,
                              BindingResult result, Model model) {

        if(result.hasErrors()) {
            List<Genre> genres = bookService.findAllGenre();
            model = ModalAttribute.setAttributeList(genres, model, "EDIT-BOOK");
            return "console";
        }

        bookService.editBook(book);
        return "redirect:/admin/console/book";
    }

    @GetMapping("console/book/remove/{id}")
    public String removeBook(@PathVariable Long id) {

        bookService.removeBook(id);
        return "redirect:/admin/console/book";
    }
}
