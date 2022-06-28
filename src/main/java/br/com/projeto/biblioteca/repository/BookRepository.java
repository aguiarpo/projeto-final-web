package br.com.projeto.biblioteca.repository;

import br.com.projeto.biblioteca.models.Book;
import br.com.projeto.biblioteca.models.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
    Page<Book> findByName(String name, Pageable pageable);
    Book findByBarcode(String barcode);
    List<Book> findAll();
}
