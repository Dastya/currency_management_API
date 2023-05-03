package ua.lipenets.currency_exchange_daemon.parser;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.springframework.stereotype.Component;
import ua.lipenets.currency_exchange_daemon.model.MonoExchangeRate;
import ua.lipenets.currency_exchange_daemon.model.dto.MonoExchangeRateDto;

@Component
public class MonoParser implements AbstractParser<MonoExchangeRateDto, MonoExchangeRate> {

    @Override
    public MonoExchangeRate toModel(MonoExchangeRateDto dto) {
        MonoExchangeRate monoExchangeRate = new MonoExchangeRate();
        monoExchangeRate.setCurrencyFrom(dto.getCurrencyFrom().equals("840")
                ? "USD" : "EUR");
        monoExchangeRate.setCurrencyTo(dto.getCurrencyTo().equals("980")
                ? "UAH" : dto.getCurrencyFrom());
        monoExchangeRate.setDate(convertDate(dto.getDate()));
        monoExchangeRate.setRateBuy(BigDecimal.valueOf(Double.parseDouble(dto.getRateBuy())));
        monoExchangeRate.setRateSell(BigDecimal.valueOf(Double.parseDouble(dto.getRateSell())));
        return monoExchangeRate;
    }

    private LocalDate convertDate(String date) {
        long unix = Long.parseLong(date);
        Instant instant = Instant.ofEpochSecond(unix);
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("Europe/Paris"));
        return zonedDateTime.toLocalDate();
    }
}
