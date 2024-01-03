package com.free.freelance_service.repo;

import com.free.freelance_service.entity.ExchangeCurrency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeCurrencyRepo extends JpaRepository<ExchangeCurrency, String> {
}
