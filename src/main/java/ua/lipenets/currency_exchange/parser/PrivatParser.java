package ua.lipenets.currency_exchange.parser;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.stereotype.Component;
import ua.lipenets.currency_exchange.model.ExchangeRate;
import ua.lipenets.currency_exchange.model.dto.PrivatBankExchangeRateDTO;

@Component
public class PrivatParser implements AbstractParser<PrivatBankExchangeRateDTO, ExchangeRate> {

    @Override
    public ExchangeRate toModel(PrivatBankExchangeRateDTO dto) {
        ExchangeRate privatExchangeRate = new ExchangeRate();
        privatExchangeRate.setCurrencyFrom(dto.getCcy());
        privatExchangeRate.setCurrencyTo(dto.getBase_ccy());
        privatExchangeRate.setRateBuy(BigDecimal.valueOf(Double.parseDouble(dto.getBuy())));
        privatExchangeRate.setRateSell(BigDecimal.valueOf(Double.parseDouble(dto.getSale())));
        privatExchangeRate.setDate(LocalDate.now());
        return privatExchangeRate;
    }
}
