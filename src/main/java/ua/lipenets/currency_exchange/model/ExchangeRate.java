package ua.lipenets.currency_exchange.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String source;

    private String currencyFrom;

    private String currencyTo;

    private BigDecimal rateBuy;

    private BigDecimal rateSell;

    private LocalDate date;
}
