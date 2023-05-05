package ua.lipenets.currency_exchange.model.dto;

import lombok.Data;

@Data
public class MinFinExchangeRateDTO {
    String id;

    String pointDate;

    String date;

    String ask;

    String bid;

    String trendAsk;

    String trendBid;

    String currency;
}
