package ua.lipenets.currency_exchange.parser;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;
import ua.lipenets.currency_exchange.model.ExchangeRate;
import ua.lipenets.currency_exchange.model.dto.MinFinExchangeRateDto;

@Component
public class MinFinParser implements AbstractParser<MinFinExchangeRateDto, ExchangeRate> {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public ExchangeRate toModel(MinFinExchangeRateDto dto) {
        ExchangeRate rate = new ExchangeRate();
        rate.setCurrencyFrom(dto.getCurrency().toUpperCase());
        rate.setCurrencyTo("UAH");
        rate.setDate(LocalDateTime.parse(dto.getDate(), formatter).toLocalDate());
        rate.setRateBuy(BigDecimal.valueOf(Double.parseDouble(dto.getBid())));
        rate.setRateSell(BigDecimal.valueOf(Double.parseDouble(dto.getAsk())));
        return rate;
    }
}
