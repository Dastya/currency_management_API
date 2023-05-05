package ua.lipenets.currency_exchange.controller;

import java.time.LocalDate;
import java.util.List;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.lipenets.currency_exchange.model.AverageRate;
import ua.lipenets.currency_exchange.model.ExchangeRate;
import ua.lipenets.currency_exchange.service.ExchangeRateService;
import ua.lipenets.currency_exchange.service.FindAverageService;

@RestController
@RequestMapping("/currency")
@RequiredArgsConstructor
public class ExchangeRateController {
    private final ExchangeRateService<ExchangeRate> service;
    private final FindAverageService findAverageService;

    @GetMapping("/average")
    @ApiOperation("Returns a list of actual courses for every source and average rate for them")
    public List<Object> getRatesWithAverageValue() {
        ExchangeRate monoUSD = service.findFirstByCurrencyDesc("USD", "MonoBank");
        ExchangeRate monoEUR = service.findFirstByCurrencyDesc("EUR", "MonoBank");
        ExchangeRate privatUSD = service.findFirstByCurrencyDesc("USD", "PrivatBank");
        ExchangeRate privatEUR = service.findFirstByCurrencyDesc("EUR", "PrivatBank");
        ExchangeRate minFinUSD = service.findFirstByCurrencyDesc("USD", "MinFin");
        ExchangeRate minFinEUR = service.findFirstByCurrencyDesc("EUR", "MinFin");
        List<ExchangeRate> ratesUSD = List.of(monoUSD,  privatUSD, minFinUSD);
        List<ExchangeRate> ratesEUR = List.of(monoEUR, privatEUR, minFinEUR);
        AverageRate averageUSD = findAverageService.findAverage(ratesUSD);
        AverageRate averageEUR = findAverageService.findAverage(ratesEUR);
        return List.of(monoUSD, monoEUR, privatUSD, privatEUR,
                minFinUSD, minFinEUR, averageUSD, averageEUR);
    }

    @GetMapping("/average_by_date")
    @ApiOperation("Returns a list of average rates for every currency for the period")
    public List<AverageRate> getRatesForAllSourcesForPeriod(@ApiParam("'yyyy-MM-dd'")
                                                            @RequestParam String from,
                                                             @ApiParam("'yyyy-MM-dd'")
                                                             @RequestParam String to) {
        AverageRate averageUSD = findAverageService
                .findAverage(service
                        .getAllByDateBetweenAndCurrencyFrom(LocalDate.parse(from),
                                LocalDate.parse(to), "USD"));
        AverageRate averageEUR = findAverageService.findAverage(service
                .getAllByDateBetweenAndCurrencyFrom(LocalDate.parse(from),
                LocalDate.parse(to), "EUR"));
        return List.of(averageUSD, averageEUR);
    }
}
