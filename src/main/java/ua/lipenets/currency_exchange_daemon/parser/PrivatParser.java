package ua.lipenets.currency_exchange_daemon.parser;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.stereotype.Component;
import ua.lipenets.currency_exchange_daemon.model.PrivatExchangeRate;
import ua.lipenets.currency_exchange_daemon.model.dto.PrivatBankExchangeRateDTO;

@Component
public class PrivatParser implements AbstractParser<PrivatBankExchangeRateDTO, PrivatExchangeRate> {

    @Override
    public PrivatExchangeRate toModel(PrivatBankExchangeRateDTO dto) {
        PrivatExchangeRate privatExchangeRate = new PrivatExchangeRate();
        privatExchangeRate.setCurrencyFrom(dto.getCcy());
        privatExchangeRate.setCurrencyTo(dto.getBase_ccy());
        privatExchangeRate.setRateBuy(BigDecimal.valueOf(Double.parseDouble(dto.getBuy())));
        privatExchangeRate.setRateSell(BigDecimal.valueOf(Double.parseDouble(dto.getSale())));
        privatExchangeRate.setDate(LocalDate.now());
        return privatExchangeRate;
    }
}
