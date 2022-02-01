package br.com.mportal.challengeMP.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.mportal.challengeMP.model.UserModel;
import br.com.mportal.challengeMP.repository.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<UserModel> optional = repository.findByLogin(login);

        if(optional.isPresent()){
            return optional.get();
        }

        throw new UsernameNotFoundException("Usuário ou senha incorreto.");
    }
}
