package br.com.projeto.biblioteca.repository;

import br.com.projeto.biblioteca.models.Book;
import br.com.projeto.biblioteca.models.User;
import br.com.projeto.biblioteca.models.enums.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
    Page<User> findByName(String name, Pageable pageable);
    Page<User> findByGenre(Genre genre, Pageable pageable);
}
