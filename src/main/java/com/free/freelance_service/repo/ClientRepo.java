package com.free.freelance_service.repo;

import com.free.freelance_service.entity.users.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<Client, String> {
    Client findByUserId (String userId);
}
