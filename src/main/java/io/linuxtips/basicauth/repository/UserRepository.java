package io.linuxtips.basicauth.repository;

import io.linuxtips.basicauth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);

}
