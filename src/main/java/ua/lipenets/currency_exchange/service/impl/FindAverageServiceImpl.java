package ua.lipenets.currency_exchange.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ua.lipenets.currency_exchange.model.AverageRate;
import ua.lipenets.currency_exchange.model.ExchangeRate;
import ua.lipenets.currency_exchange.service.ExchangeRateService;
import ua.lipenets.currency_exchange.service.FindAverageService;

@Service
@RequiredArgsConstructor
public class FindAverageServiceImpl implements FindAverageService {
    private final ExchangeRateService<ExchangeRate> service;

    @Value("${mono.name}")
    private final String MONO_NAME;

    @Value("${privat.name}")
    private final String PRIVAT_NAME;

    @Value("${minfin.name}")
    private final String MINFIN_NAME;

    @Override
    public List<AverageRate> getAverageCourses() {
        ExchangeRate monoUSD = service.findFirstByCurrencyDesc("USD", MONO_NAME);
        ExchangeRate monoEUR = service.findFirstByCurrencyDesc("EUR", MONO_NAME);
        ExchangeRate privatUSD = service.findFirstByCurrencyDesc("USD", PRIVAT_NAME);
        ExchangeRate privatEUR = service.findFirstByCurrencyDesc("EUR", PRIVAT_NAME);
        ExchangeRate minFinUSD = service.findFirstByCurrencyDesc("USD", MINFIN_NAME);
        ExchangeRate minFinEUR = service.findFirstByCurrencyDesc("EUR", MINFIN_NAME);
        List<ExchangeRate> ratesUSD = List.of(monoUSD,  privatUSD, minFinUSD);
        List<ExchangeRate> ratesEUR = List.of(monoEUR, privatEUR, minFinEUR);
        return List.of(findAverage(ratesUSD), findAverage(ratesEUR));
    }

    @Override
    public List<AverageRate> getAverageCoursesByPeriod(String from, String to) {
        AverageRate averageUSD = findAverage(service
                        .getAllByDateBetweenAndCurrencyFrom(LocalDate.parse(from),
                                LocalDate.parse(to), "USD"));
        AverageRate averageEUR = findAverage(service
                .getAllByDateBetweenAndCurrencyFrom(LocalDate.parse(from),
                        LocalDate.parse(to), "EUR"));
        return List.of(averageUSD, averageEUR);
    }

    private AverageRate findAverage(List<ExchangeRate> rates) {
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
