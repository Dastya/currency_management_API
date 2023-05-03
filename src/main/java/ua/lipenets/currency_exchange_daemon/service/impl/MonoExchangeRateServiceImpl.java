package ua.lipenets.currency_exchange_daemon.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.lipenets.currency_exchange_daemon.model.MonoExchangeRate;
import ua.lipenets.currency_exchange_daemon.repository.ExchangeRateRepository;
import ua.lipenets.currency_exchange_daemon.service.ExchangeRateService;

@Service
@RequiredArgsConstructor
public class MonoExchangeRateServiceImpl implements ExchangeRateService<MonoExchangeRate> {

    private final ExchangeRateRepository<MonoExchangeRate> repository;

    @Override
    public MonoExchangeRate save(MonoExchangeRate monoExchangeRate) {
        return repository.save(monoExchangeRate);
    }

    @Override
    public MonoExchangeRate getById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new NoSuchElementException(String.format("Can't find rate by id: %s", id)));
    }

    @Override
    public List<MonoExchangeRate> getAll() {
        return repository.findAll();
    }

    @Override
    public MonoExchangeRate update(Long id, MonoExchangeRate monoExchangeRate) {
        MonoExchangeRate rate = repository.findById(id).orElseThrow(() ->
                new NoSuchElementException(String.format("Can't find rate by id: %d", id)));
        rate.setCurrencyFrom(monoExchangeRate.getCurrencyFrom());
        rate.setCurrencyTo(monoExchangeRate.getCurrencyTo());
        rate.setRateSell(monoExchangeRate.getRateSell());
        rate.setRateBuy(monoExchangeRate.getRateBuy());
        rate.setDate(monoExchangeRate.getDate());
        return repository.save(rate);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

}
