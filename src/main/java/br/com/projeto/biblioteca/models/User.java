package br.com.projeto.biblioteca.models;

import br.com.projeto.biblioteca.models.enums.TypeUsers;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
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
    @Email
    private String email;
    @Size(min = 8)
    private String password;
    @Enumerated(EnumType.STRING)
    private TypeUsers type;
    @OneToMany(mappedBy = "book")
    private List<Loan> loans = new ArrayList<Loan>();
}
