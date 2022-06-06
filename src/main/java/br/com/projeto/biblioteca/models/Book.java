package br.com.projeto.biblioteca.models;

import br.com.projeto.biblioteca.models.enums.Genre;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Integer year;
    private String barcode;
    private String name;
    private String author;
    private String edition;
    private String status;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @OneToMany(mappedBy = "user")
    private List<Loan> loans = new ArrayList<Loan>();
}
