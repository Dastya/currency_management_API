package ua.lipenets.currency_exchange.service;

import java.util.List;
import ua.lipenets.currency_exchange.model.AverageRate;
import ua.lipenets.currency_exchange.model.ExchangeRate;

public interface FindAverageService {
    AverageRate findAverage(List<ExchangeRate> rates);
}
