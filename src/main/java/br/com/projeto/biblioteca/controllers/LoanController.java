package br.com.projeto.biblioteca.controllers;

import br.com.projeto.biblioteca.models.Book;
import br.com.projeto.biblioteca.models.Loan;
import br.com.projeto.biblioteca.service.BookService;
import br.com.projeto.biblioteca.service.LoanService;
import br.com.projeto.biblioteca.service.UserService;
import br.com.projeto.biblioteca.statics.ModalAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("admin")
public class LoanController {

    @Autowired
    private BookService bookService;
    @Autowired
    private LoanService loanService;

    @GetMapping("console/loan")
    public String getBook(Model model, Pageable pageable) {

        Page<Loan> loans = loanService.findAll(pageable);
        model = ModalAttribute.setAttribute(loans, model, "LOAN");
        return "console";
    }

    @GetMapping("console/loan/devolved/{id}/{check}")
    public String getBook(@PathVariable Long id, @PathVariable Boolean check) {

        Optional<Loan> loanOptional = loanService.findById(id);
        Loan loan = loanOptional.get();
        loan.setLending(!check);
        loanService.addAndEditLoan(loan);
        return "redirect:/admin/console/loan";
    }

    @GetMapping("console/loan/retired/{id}/{check}")
    public String getBook2(@PathVariable Long id, @PathVariable Boolean check) {

        Optional<Loan> loanOptional = loanService.findById(id);
        Loan loan = loanOptional.get();
        loan.setRetired(check);
        loanService.addAndEditLoan(loan);
        return "redirect:/admin/console/loan";
    }

    @GetMapping("console/loan/{email}")
    public String getBook(Model model, Pageable pageable, @PathVariable String email) {

        Page<Loan> loans = loanService.findByUserEmail(pageable, email);
        model = ModalAttribute.setAttribute(loans, model, "LOAN");
        return "console";
    }
}
