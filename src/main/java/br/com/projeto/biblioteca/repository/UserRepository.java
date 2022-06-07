package br.com.projeto.biblioteca.repository;

import br.com.projeto.biblioteca.models.User;
import br.com.projeto.biblioteca.models.enums.TypeUsers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findByEmail(String email);
    Page<User> findByNameStartingWith(String name, Pageable pageable);
    Optional<User> findByEmailAndPassword(String email, String password);
    Page<User> findByType(TypeUsers type, Pageable pageable);
}
