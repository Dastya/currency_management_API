package ua.lipenets.currency_exchange_daemon.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.lipenets.currency_exchange_daemon.model.PrivatExchangeRate;
import ua.lipenets.currency_exchange_daemon.repository.ExchangeRateRepository;
import ua.lipenets.currency_exchange_daemon.service.ExchangeRateService;

@Service
@RequiredArgsConstructor
public class PrivatExchangeRateServiceImpl implements ExchangeRateService<PrivatExchangeRate> {

    private final ExchangeRateRepository<PrivatExchangeRate> repository;


    @Override
    public PrivatExchangeRate save(PrivatExchangeRate exchangeRate) {
        return repository.save(exchangeRate);
    }

    @Override
    public PrivatExchangeRate getById(Long id) {
        return repository.findById(id).orElseThrow( () ->
                new NoSuchElementException(String.format("Can't find rate by id: %s", id)));
    }

    @Override
    public List<PrivatExchangeRate> getAll() {
        return repository.findAll();
    }

    @Override
    public PrivatExchangeRate update(Long id, PrivatExchangeRate exchangeRate) {
        PrivatExchangeRate rate = repository.findById(id).orElseThrow(() ->
                new NoSuchElementException(String.format("Can't find rate by id: %d", id)));
        rate.setCurrencyFrom(exchangeRate.getCurrencyFrom());
        rate.setCurrencyTo(exchangeRate.getCurrencyTo());
        rate.setRateSell(exchangeRate.getRateSell());
        rate.setRateBuy(exchangeRate.getRateBuy());
        rate.setDate(exchangeRate.getDate());
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
