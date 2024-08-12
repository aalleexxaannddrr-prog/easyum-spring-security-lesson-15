package com.easyum.springsec.repo;

import com.easyum.springsec.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsernameAndPassword(String username, String password);

    UserEntity findByUsername(String username);
}
