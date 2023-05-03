package ua.lipenets.currency_exchange_daemon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRateRepository<M> extends JpaRepository<M, Long>{
}
