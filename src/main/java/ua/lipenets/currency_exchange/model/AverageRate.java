package ua.lipenets.currency_exchange.model;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class AverageRate {
    private String currencyFrom;

    private String currencyTo;

    private BigDecimal rateBuy;

    private BigDecimal rateSell;
}
