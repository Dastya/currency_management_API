package ua.lipenets.currency_exchange_daemon.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "mono_bank")
public class MonoExchangeRate {
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
