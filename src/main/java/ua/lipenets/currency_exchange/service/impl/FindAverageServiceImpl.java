package ua.lipenets.currency_exchange.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.lipenets.currency_exchange.model.AverageRate;
import ua.lipenets.currency_exchange.model.ExchangeRate;
import ua.lipenets.currency_exchange.service.FindAverageService;

@Service
@RequiredArgsConstructor
public class FindAverageServiceImpl implements FindAverageService {
    @Override
    public AverageRate findAverage(List<ExchangeRate> rates) {
        BigDecimal sumBuy = BigDecimal.ZERO;
        BigDecimal sumSell = BigDecimal.ZERO;
        for (ExchangeRate rate : rates) {
            sumBuy = sumBuy.add(rate.getRateBuy());
            sumSell = sumSell.add(rate.getRateSell());
        }
        AverageRate averageRate = new AverageRate();
        averageRate.setRateBuy(sumBuy.divide(BigDecimal.valueOf(rates.size()), 2, RoundingMode.HALF_UP));
        averageRate.setRateSell(sumSell.divide(BigDecimal.valueOf(rates.size()), 2, RoundingMode.HALF_UP));
        averageRate.setCurrencyFrom(rates.get(0).getCurrencyFrom());
        averageRate.setCurrencyTo("UAH");
        return averageRate;

    }

}
