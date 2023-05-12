package ua.lipenets.currency_exchange.model.dto;

import lombok.Data;

@Data
public class MinFinExchangeRateDto {
    private String id;

    private String pointDate;

    private String date;

    private String ask;

    private String bid;

    private String trendAsk;

    private String trendBid;

    private String currency;
}
