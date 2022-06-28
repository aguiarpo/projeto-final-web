package br.com.projeto.biblioteca.service;

import br.com.projeto.biblioteca.dto.BookRegister;
import br.com.projeto.biblioteca.models.Book;
import br.com.projeto.biblioteca.models.Loan;
import br.com.projeto.biblioteca.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    public void addAndEditLoan(Loan loan){
        loanRepository.save(loan);
    }

    public Page<Loan> findAll(Pageable pageable){
        return  loanRepository.findAll(pageable);
    }

    public Page<Loan> findByUserEmail(Pageable pageable, String email){
        return  loanRepository.findByUserEmailStartingWith(pageable, email);
    }


    public Optional<Loan> findById(Long id){
        return  loanRepository.findById(id);
    }
}
