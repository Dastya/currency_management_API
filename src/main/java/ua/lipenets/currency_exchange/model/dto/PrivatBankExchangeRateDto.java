package ua.lipenets.currency_exchange.model.dto;

import lombok.Data;

@Data
public class PrivatBankExchangeRateDto {
    private String ccy;

    private String base_ccy;

    private String buy;

    private String sale;
}
