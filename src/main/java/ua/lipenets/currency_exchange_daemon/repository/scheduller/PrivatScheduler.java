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
import ua.lipenets.currency_exchange_daemon.model.PrivatExchangeRate;
import ua.lipenets.currency_exchange_daemon.model.dto.PrivatBankExchangeRateDTO;
import ua.lipenets.currency_exchange_daemon.parser.AbstractParser;
import ua.lipenets.currency_exchange_daemon.service.ExchangeRateService;

@Log4j2
@Component
@RequiredArgsConstructor
public class PrivatScheduler {
    private final AbstractParser<PrivatBankExchangeRateDTO, PrivatExchangeRate> parser;
    private final ExchangeRateService<PrivatExchangeRate> service;

    @Scheduled(fixedDelay = 100000)
    public void job() {
        Gson gson = new Gson();
        HttpRequest getRequest = null;
        try {
            getRequest = HttpRequest.newBuilder()
                    .uri(new URI("https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5"))
                    .GET()
                    .build();
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> getResponse = httpClient
                    .send(getRequest, HttpResponse.BodyHandlers.ofString());
            PrivatBankExchangeRateDTO[] privatBankExchangeRateDTOList = gson
                    .fromJson(getResponse.body(), PrivatBankExchangeRateDTO[].class);
            PrivatExchangeRate privatExchangeRateEUR = parser.toModel(privatBankExchangeRateDTOList[0]);
            privatExchangeRateEUR.setSource("PrivatBank");
            PrivatExchangeRate privatExchangeRateUSD = parser.toModel(privatBankExchangeRateDTOList[1]);
            privatExchangeRateUSD.setSource("PrivatBank");
            if (service.existsById(3L)) {
                service.update(3L, privatExchangeRateUSD);
            } else {
                service.save(privatExchangeRateUSD);
            }
            if (service.existsById(4L)) {
                service.update(4L, privatExchangeRateEUR);
            } else {
                service.save(privatExchangeRateEUR);
            }
            System.out.println("privat is working");
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException("Can't get data from source");
        }
    }
}
