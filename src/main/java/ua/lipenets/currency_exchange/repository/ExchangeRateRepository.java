package ua.lipenets.currency_exchange.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lipenets.currency_exchange.model.ExchangeRate;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
    ExchangeRate findBySourceAndCurrencyFrom(String source, String currencyFrom);

    boolean existsByCurrencyFromAndSource(String currencyFrom, String source);

    List<ExchangeRate> findAllByCurrencyFrom(String currencyBy);

    ExchangeRate findFirstByCurrencyFromAndSourceOrderByIdDesc(String currencyFrom, String source);

    List<ExchangeRate> getAllByDateBetweenAndCurrencyFrom(LocalDate from, LocalDate to, String currencyFrom);

}
