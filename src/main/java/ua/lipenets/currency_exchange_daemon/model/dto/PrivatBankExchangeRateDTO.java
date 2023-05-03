package ua.lipenets.currency_exchange_daemon.model.dto;

import lombok.Data;

@Data
public class PrivatBankExchangeRateDTO {
    private String ccy;
    private String base_ccy;
    private String buy;
    private String sale;
}
