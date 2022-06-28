package br.com.projeto.biblioteca.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "loans")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate initialDate = LocalDate.now();
    private LocalDate finalDate = LocalDate.now().plusDays(7);
    private Boolean lending = true;
    private Boolean retired = false;
    @ManyToOne
    private User user;
    @ManyToOne
    private Book book;
}
