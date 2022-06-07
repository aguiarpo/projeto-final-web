package br.com.projeto.biblioteca.models;

import br.com.projeto.biblioteca.models.enums.TypeUsers;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    @Column(unique = true)
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Enumerated(EnumType.STRING)
    private TypeUsers type;
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Loan> loans = new ArrayList<Loan>();
}
