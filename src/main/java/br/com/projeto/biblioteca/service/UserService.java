package br.com.projeto.biblioteca.service;

import br.com.projeto.biblioteca.dto.UserRegister;
import br.com.projeto.biblioteca.models.User;
import br.com.projeto.biblioteca.models.enums.TypeUsers;
import br.com.projeto.biblioteca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(UserRegister userPost){
        if(userRepository.findByEmail(userPost.getEmail()) == null){
            User userRegister = userPost.toUser();
            userRegister.setType(TypeUsers.USER);
            userRepository.save(userRegister);
        }
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public Page<User> findByEmailPage(String email, Pageable pageable){
        return userRepository.findByEmail(email, pageable);
    }

    public Page<User> findAll(Pageable pageable){
        return userRepository.findAll(pageable);
    }
}
