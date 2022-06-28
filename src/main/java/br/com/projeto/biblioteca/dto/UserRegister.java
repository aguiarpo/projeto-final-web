package br.com.projeto.biblioteca.dto;

import br.com.projeto.biblioteca.models.User;
import br.com.projeto.biblioteca.models.enums.TypeUsers;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserRegister {
    @NotBlank(message = "Campo nome é obrigatório")
    private String name;
    @Email(message = "Email inválido")
    @NotBlank(message = "Campo nome é obrigatório")
    private String email;
    @Size(min = 8, message = "Senha deve conter mais que 8 caracteres")
    @NotBlank(message = "Campo nome é obrigatório")
    private String password;

    public User toUser() {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }
}
