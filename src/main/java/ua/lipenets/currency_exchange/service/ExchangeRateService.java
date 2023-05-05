package ua.lipenets.currency_exchange.service;

import java.time.LocalDate;
import java.util.List;

public interface ExchangeRateService<M> {
    M save(M exchangeRate);

    M getById(Long id);

    List<M> getAll();

    M update(Long id, M exchangeRate);

    M findBySourceAndCurrencyFrom(String source, String currencyFrom);

    List<M> getAllByDateBetweenAndCurrencyFrom(LocalDate from, LocalDate to, String currencyFrom);


    List<M> findAllByCurrencyFrom(String currencyBy);

    M findFirstByCurrencyDesc(String currencyFrom, String source);

    boolean existsByCurrencyFromAndSource(String currencyFrom, String source);

    void delete(Long id);

    boolean existsById(Long id);
}
