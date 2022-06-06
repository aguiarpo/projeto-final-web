package br.com.projeto.biblioteca.models;

import br.com.projeto.biblioteca.models.enums.TypeUsers;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private TypeUsers type;
    @OneToMany(mappedBy = "book")
    private List<Loan> loans = new ArrayList<Loan>();
}
