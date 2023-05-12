package ua.lipenets.currency_exchange.service;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.lipenets.currency_exchange.model.ExchangeRate;
import ua.lipenets.currency_exchange.parser.AbstractParser;

@Component
@RequiredArgsConstructor
public class GetDataFromApiService<D> {
    private final ExchangeRateService<ExchangeRate> service;

    public D[] getData(String apiLink, Class<D[]> clazz) {
        Gson gson = new Gson();
        HttpRequest getRequest = null;
        try {
            getRequest = HttpRequest.newBuilder()
                    .uri(new URI(apiLink))
                    .GET()
                    .build();
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> getResponse = httpClient
                    .send(getRequest, HttpResponse.BodyHandlers.ofString());
            return gson.fromJson(getResponse.body(), clazz);
        } catch (URISyntaxException | InterruptedException | IOException e) {
            throw new RuntimeException("Can't get data from source");
        }
    }

    public void saveData(D[] dtoList, String sourceName, AbstractParser<D, ExchangeRate> parser) {
        ExchangeRate exchangeRateUSD = parser.toModel(dtoList[0]);
        exchangeRateUSD.setSource(sourceName);
        ExchangeRate exchangeRateEUR = parser.toModel(dtoList[1]);
        exchangeRateEUR.setSource(sourceName);
        service.save(exchangeRateUSD);
        service.save(exchangeRateEUR);
    }
}
