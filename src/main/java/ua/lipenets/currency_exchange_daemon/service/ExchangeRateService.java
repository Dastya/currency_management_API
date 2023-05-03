package ua.lipenets.currency_exchange_daemon.service;

import java.util.List;

public interface ExchangeRateService<M> {
    M save(M exchangeRate);

    M getById(Long id);

    List<M> getAll();

    M update(Long id, M exchangeRate);

    void delete(Long id);

    boolean existsById(Long id);
}
