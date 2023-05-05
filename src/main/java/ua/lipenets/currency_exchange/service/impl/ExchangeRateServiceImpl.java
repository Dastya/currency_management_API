package ua.lipenets.currency_exchange.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.lipenets.currency_exchange.model.ExchangeRate;
import ua.lipenets.currency_exchange.repository.ExchangeRateRepository;
import ua.lipenets.currency_exchange.service.ExchangeRateService;

@Service
@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService<ExchangeRate> {

    private final ExchangeRateRepository repository;

    @Override
    public ExchangeRate save(ExchangeRate exchangeRate) {
        return repository.save(exchangeRate);
    }

    @Override
    public ExchangeRate getById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new NoSuchElementException(String.format("Can't find rate by id: %s", id)));
    }

    @Override
    public List<ExchangeRate> getAll() {
        return repository.findAll();
    }

    @Override
    public ExchangeRate update(Long id, ExchangeRate exchangeRate) {
        ExchangeRate rate = repository.findById(id).orElseThrow(() ->
                new NoSuchElementException(String.format("Can't find rate by id: %s", id)));
        rate.setCurrencyFrom(exchangeRate.getCurrencyFrom());
        rate.setCurrencyTo(exchangeRate.getCurrencyTo());
        rate.setRateSell(exchangeRate.getRateSell());
        rate.setRateBuy(exchangeRate.getRateBuy());
        rate.setDate(exchangeRate.getDate());
        return rate;
    }

    @Override
    public ExchangeRate findBySourceAndCurrencyFrom(String source, String currencyFrom) {
        return repository.findBySourceAndCurrencyFrom(source, currencyFrom);
    }

    @Override
    public List<ExchangeRate> getAllByDateBetweenAndCurrencyFrom(LocalDate from, LocalDate to, String currencyFrom) {
        return repository.getAllByDateBetweenAndCurrencyFrom(from, to, currencyFrom);
    }


    @Override
    public List<ExchangeRate> findAllByCurrencyFrom(String currencyBy) {
        return repository.findAllByCurrencyFrom(currencyBy);
    }

    @Override
    public ExchangeRate findFirstByCurrencyDesc(String currencyFrom, String source) {
        return repository.findFirstByCurrencyFromAndSourceOrderByIdDesc(currencyFrom, source);
    }

    @Override
    public boolean existsByCurrencyFromAndSource(String currencyFrom, String source) {
        return repository.existsByCurrencyFromAndSource(currencyFrom, source);
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
