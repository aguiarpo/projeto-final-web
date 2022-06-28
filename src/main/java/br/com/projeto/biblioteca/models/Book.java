package br.com.projeto.biblioteca.models;

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
    private Short year;
    @Column(unique = true)
    private String barcode;
    private String name;
    private String author;
    private Byte edition;
    private String url;
    //private Integer quant = 1;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "genres_books",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres = new ArrayList<Genre>();
    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE)
    private List<Loan> loans = new ArrayList<Loan>();
}
