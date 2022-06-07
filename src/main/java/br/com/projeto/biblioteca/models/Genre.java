package br.com.projeto.biblioteca.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    @Column(unique = true)
    private String name;
    @ManyToMany(mappedBy = "genres")
    private List<Book> books = new ArrayList<Book>();
}