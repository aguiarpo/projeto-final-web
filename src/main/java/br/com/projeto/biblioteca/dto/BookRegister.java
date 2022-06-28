package br.com.projeto.biblioteca.dto;

import br.com.projeto.biblioteca.models.Book;
import br.com.projeto.biblioteca.models.Genre;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class BookRegister {
    private Long id;


    @Max(value = 3000, message = "O valor máximo deve ser o ano atual")
    @Min(value = 1000, message = "O valor mínimo deve ser o ano 1000")
    @NotNull(message = "Campo ano é obrigatório")
    private Short year;
    @Pattern(regexp = "[0-9]+", message = "Apenas números")
    @NotBlank(message = "O campo código de barras é obrigatório")
    private String barcode;
    @NotBlank(message = "O campo nome é obrigatório")
    private String name;
    @NotBlank(message = "O campo autor é obrigatório")
    private String author;
    @NotBlank(message = "O campo url é obrigatório")
    private String url;
    @Max(value = 20, message = "O valor máximo deve ser 20")
    @Min(value = 1, message = "O valor mínimo deve ser 1")
    @NotNull(message = "Campo edição é obrigatório")
    private Byte edition = 1;
    @NotEmpty(message = "Atribua ao menos um gênero ao livro")
    private List<Short> genres;
    @Min(value = 1, message = "O valor mínimo deve ser 1")
    private Integer quant = 1;

    public Book toBook(){
        Book book = new Book();
        book.setName(name);
        book.setQuant(quant);
        book.setAuthor(author);
        book.setEdition(edition);
        book.setYear(year);
        book.setUrl(url);
        book.setBarcode(barcode);
        List<Genre> genreList = new ArrayList<Genre>();
        for (Short id: genres) {
            Genre genre = new Genre();
            genre.setId(id);
            genreList.add(genre);
        }
        book.setGenres(genreList);
        return book;
    }

    public Book toBookWithId(){
        Book book = new Book();
        book.setId(id);
        book.setName(name);
        book.setAuthor(author);
        book.setEdition(edition);
        book.setQuant(quant);
        book.setYear(year);
        book.setUrl(url);
        book.setBarcode(barcode);
        List<Genre> genreList = new ArrayList<Genre>();
        for (Short id: genres) {
            Genre genre = new Genre();
            genre.setId(id);
            genreList.add(genre);
        }
        book.setGenres(genreList);
        return book;
    }
}
