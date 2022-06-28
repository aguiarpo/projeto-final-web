package br.com.projeto.biblioteca.configs;

import br.com.projeto.biblioteca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        br.com.projeto.biblioteca.models.User user = Optional.ofNullable(userRepository.findByEmail(email))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<GrantedAuthority> authorityListAdmin = AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_LIBRARIAN", "ROLE_USER");
        List<GrantedAuthority> authorityListLibrarian = AuthorityUtils.createAuthorityList("ROLE_LIBRARIAN");
        List<GrantedAuthority> authorityListUser = AuthorityUtils.createAuthorityList("ROLE_USER");
        List<GrantedAuthority> nivelUser = null;
        switch (user.getType()){
            case LIBRARIAN:
                nivelUser = authorityListLibrarian;
                break;
            case ADMIN:
                nivelUser = authorityListAdmin;
                break;
            case USER:
                nivelUser = authorityListUser;
                break;
        }
        return new User(user.getEmail(), user.getPassword(),nivelUser);
    }
}