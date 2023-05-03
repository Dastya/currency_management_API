package ua.lipenets.currency_exchange_daemon.repository.scheduller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ua.lipenets.currency_exchange_daemon.model.MonoExchangeRate;
import ua.lipenets.currency_exchange_daemon.model.dto.MonoExchangeRateDto;
import ua.lipenets.currency_exchange_daemon.parser.AbstractParser;
import ua.lipenets.currency_exchange_daemon.service.ExchangeRateService;

@Log4j2
@Component
@RequiredArgsConstructor
public class MonoScheduler {
    private final AbstractParser<MonoExchangeRateDto, MonoExchangeRate> parser;
    private final ExchangeRateService<MonoExchangeRate> service;

    @Scheduled(fixedDelay = 100000)
    public void job() {
        Gson gson = new Gson();
        HttpRequest getRequest = null;
        try {
            getRequest = HttpRequest.newBuilder()
                    .uri(new URI("https://api.monobank.ua/bank/currency"))
                    .GET()
                    .build();
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> getResponse = httpClient
                    .send(getRequest, HttpResponse.BodyHandlers.ofString());
            MonoExchangeRateDto[] monoExchangeRateDtoList = gson
                    .fromJson(getResponse.body(), MonoExchangeRateDto[].class);
            MonoExchangeRate monoExchangeRateUSD = parser.toModel(monoExchangeRateDtoList[0]);
            monoExchangeRateUSD.setSource("MonoBank");
            MonoExchangeRate monoExchangeRateEUR = parser.toModel(monoExchangeRateDtoList[1]);
            monoExchangeRateEUR.setSource("MonoBank");
            if (service.existsById(1L)) {
                service.update(1L, monoExchangeRateUSD);
            } else {
                service.save(monoExchangeRateUSD);
            }
            if (service.existsById(2L)) {
                service.update(2L, monoExchangeRateEUR);
            } else {
                service.save(monoExchangeRateEUR);
            }
            System.out.println("mono is working");

        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException("Can't get data from source");
        }
    }
}
