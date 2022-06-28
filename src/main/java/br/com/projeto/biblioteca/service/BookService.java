package br.com.projeto.biblioteca.service;

import br.com.projeto.biblioteca.dto.BookRegister;
import br.com.projeto.biblioteca.dto.GenreRegister;
import br.com.projeto.biblioteca.models.Book;
import br.com.projeto.biblioteca.models.Genre;
import br.com.projeto.biblioteca.repository.BookRepository;
import br.com.projeto.biblioteca.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GenreRepository genreRepository;

    public Page<Book> findAllBook(Pageable pageable){
        return bookRepository.findAll(pageable);
    }

    public Page<Genre> findAllGenre(Pageable pageable){
        return genreRepository.findAll(pageable);
    }

    public List<Genre> findAllGenre(){
        return genreRepository.findAll();
    }

    public List<Book> findAllBook(){
        return bookRepository.findAll();
    }

    public Optional<Genre> findByIdGenre(Short id){
        return genreRepository.findById(id);
    }

    public Optional<Book> findByIdBook(Long id){
        return bookRepository.findById(id);
    }

    public void addGenre(GenreRegister genreRegister){
        if(genreRepository.findByName(genreRegister.getName()) == null){
            Genre genre = genreRegister.toGenre();
            genreRepository.save(genre);
        }
    }

    public void addBook(BookRegister bookRegister){
        if(bookRepository.findByBarcode(bookRegister.getBarcode()) == null){
            Book book = bookRegister.toBook();
            bookRepository.save(book);
        }
    }

    public void editBook(BookRegister bookRegister){
        if(bookRepository.findById(bookRegister.getId()).isPresent()){
            Book book = bookRegister.toBookWithId();
            bookRepository.save(book);
        }
    }

    public void editBook(Book book){
        if(bookRepository.findById(book.getId()).isPresent()){
            bookRepository.save(book);
        }
    }

    public void editGenre(GenreRegister genreRegister){
        if(genreRepository.findByName(genreRegister.getName()) == null){
            Genre genre = genreRegister.toGenreWithId();
            genreRepository.save(genre);
        }
    }

    public void removeGenre(Short id){
        if(genreRepository.findById(id).isPresent()){
            genreRepository.deleteById(id);
        }
    }

    public void removeBook(Long id){
        if(bookRepository.findById(id).isPresent()){
            bookRepository.deleteById(id);
        }
    }
}
