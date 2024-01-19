package com.free.freelance_service.repo;

import com.free.freelance_service.entity.users.Performer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformerRepo extends JpaRepository<Performer, String> {
    Performer findByUserId (String userId);
}
