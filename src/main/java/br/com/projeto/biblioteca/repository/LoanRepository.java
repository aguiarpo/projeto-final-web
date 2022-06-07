package br.com.projeto.biblioteca.repository;

import br.com.projeto.biblioteca.models.Loan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends CrudRepository<Loan, Long> {
}
