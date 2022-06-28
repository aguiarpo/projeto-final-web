package br.com.projeto.biblioteca.repository;

import br.com.projeto.biblioteca.models.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends PagingAndSortingRepository<Loan, Long> {
    Page<Loan> findByUserEmailStartingWith(Pageable pageable, String email);

    @Query(value = "SELECT count(id) FROM loans WHERE retired = true AND book_id=:id",nativeQuery = true)
    int findByCount(@Param("id") Long id);
}
