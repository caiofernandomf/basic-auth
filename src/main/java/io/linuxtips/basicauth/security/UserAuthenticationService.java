package io.linuxtips.basicauth.security;

import io.linuxtips.basicauth.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import io.linuxtips.basicauth.model.User;

@Service
public class UserAuthenticationService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserAuthenticationService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User inDB = userRepository.findByUsername(username);
        if(inDB==null){
            throw new UsernameNotFoundException("user not found");
        }
        return new AppUser(inDB);
    }
}
