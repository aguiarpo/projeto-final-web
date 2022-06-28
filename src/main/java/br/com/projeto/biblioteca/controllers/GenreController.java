package br.com.projeto.biblioteca.controllers;

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
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin")
public class GenreController {

    @Autowired
    private BookService bookService;

    @GetMapping("console/genre")
    public String getAllGenre(Model model, Pageable pageable) {

        Page<Genre> genres = bookService.findAllGenre(pageable);
        model = ModalAttribute.setAttribute(genres, model, "GENRE");
        return "console";
    }

    @GetMapping("console/genre/add")
    public String addGenre(@ModelAttribute("genre") GenreRegister genre, Model model) {
        model = ModalAttribute.setAttribute(null, model, "ADD-GENRE");
        return "console";
    }

    @PostMapping("console/genre/add")
    public String addGenrePost(@Valid @ModelAttribute("genre") GenreRegister genre,
                               BindingResult result, Model model) {

        if(result.hasErrors()) {
            model = ModalAttribute.setAttribute(null, model, "ADD-GENRE");
            return "console";
        }
        bookService.addGenre(genre);
        return "redirect:/admin/console/genre";
    }

    @GetMapping("console/genre/remove/{id}")
    public String removeGenre(@PathVariable Short id) {
        Optional<Genre> genre =bookService.findByIdGenre(id);
        for (Book book: genre.get().getBooks()) {
            List<Genre> genres = book.getGenres();
            for(int i = 0; i < genres.size(); i++) {
                if(genres.get(i).getId() == id){
                    genres.remove(i);
                }
            }
            book.setGenres(genres);
            bookService.editBook(book);
        }
        bookService.removeGenre(id);
        return "redirect:/admin/console/genre";
    }

    @GetMapping("console/genre/edit/{id}")
    public String editGenre(@ModelAttribute("genre") GenreRegister genre,
                              @PathVariable Short id, Model model) {
        Optional<Genre> genreOptional = bookService.findByIdGenre(id);
        model = ModalAttribute.setAttributeWithoutPage(genreOptional, model, "EDIT-GENRE");
        genre.setName(genreOptional.get().getName());
        return "console";
    }

    @PostMapping("console/genre/edit")
    public String ediGenrePost(@Valid @ModelAttribute("genre") GenreRegister genre,
                               BindingResult result, Model model) {

        if(result.hasErrors()) {
            model = ModalAttribute.setAttribute(null, model, "EDIT-GENRE");
            return "console";
        }
        bookService.editGenre(genre);
        return "redirect:/admin/console/genre";
    }
}
