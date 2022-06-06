package br.com.projeto.biblioteca.repository;

import br.com.projeto.biblioteca.models.Book;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<Book, Long> {
}
