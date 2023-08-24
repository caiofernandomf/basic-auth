package io.linuxtips.basicauth.service;

import io.linuxtips.basicauth.model.User;
import io.linuxtips.basicauth.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    final UserRepository userRepository;

    final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }

    public User save(User user){
        user.setPassword(
                this.passwordEncoder.encode(
                        user.getPassword()
                )
        );
        return userRepository.save(user);
    }

}
