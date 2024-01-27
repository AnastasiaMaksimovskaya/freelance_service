package com.free.freelance_service.repo;

import com.free.freelance_service.entity.TempUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TempUserRepo extends JpaRepository<TempUser, String> {
}
