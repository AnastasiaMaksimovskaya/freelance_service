package com.free.freelance_service.repo;

import com.free.freelance_service.entity.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {

}
