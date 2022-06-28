package br.com.projeto.biblioteca.repository;

import br.com.projeto.biblioteca.models.Genre;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends PagingAndSortingRepository<Genre, Short> {
    Genre findByName(String name);
    List<Genre> findAll();
}
