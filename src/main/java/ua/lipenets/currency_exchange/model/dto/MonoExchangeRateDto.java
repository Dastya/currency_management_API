package ua.lipenets.currency_exchange.model.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class MonoExchangeRateDto {
    @SerializedName("currencyCodeA")
    private String currencyFrom;

    @SerializedName("currencyCodeB")
    private String currencyTo;

    private String date;

    private String rateBuy;

    private String rateCross;

    private String rateSell;
}
