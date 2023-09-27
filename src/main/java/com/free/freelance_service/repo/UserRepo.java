package com.free.freelance_service.repo;

import com.free.freelance_service.entity.users.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserCredentials, String> {
    UserCredentials findFirstByLogin(String login);

}
