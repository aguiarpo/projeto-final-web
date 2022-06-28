package br.com.projeto.biblioteca.dto;

import br.com.projeto.biblioteca.models.Genre;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class GenreRegister {
    private Short id;

    @NotBlank(message = "Campo nome é obrigatório")
    private String name;

    public Genre toGenre() {
        Genre genre = new Genre();
        genre.setName(name);
        return genre;
    }

    public Genre toGenreWithId() {
        Genre genre = new Genre();
        genre.setId(id);
        genre.setName(name);
        return genre;
    }
}
