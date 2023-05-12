package ua.lipenets.currency_exchange.scheduller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ua.lipenets.currency_exchange.model.ExchangeRate;
import ua.lipenets.currency_exchange.model.dto.MinFinExchangeRateDto;
import ua.lipenets.currency_exchange.model.dto.MonoExchangeRateDto;
import ua.lipenets.currency_exchange.model.dto.PrivatBankExchangeRateDto;
import ua.lipenets.currency_exchange.parser.AbstractParser;
import ua.lipenets.currency_exchange.service.GetDataFromApiService;

@Component
@RequiredArgsConstructor
public class SaveDataToDBScheduler {
    @Value("${mono.url}")
    private String MONO_LINK;

    @Value("${mono.name}")
    private String MONO_NAME;

    @Value("${privat.url}")
    private String PRIVAT_LINK;

    @Value("${privat.name}")
    private String PRIVAT_NAME;

    @Value("${minfin.url}")
    private String MINFIN_LINK;

    @Value("${minfin.name}")
    private String MINFIN_NAME;

    private final GetDataFromApiService<MonoExchangeRateDto>
            monoExchangeRateDtoGetDataFromApiService;

    private final GetDataFromApiService<PrivatBankExchangeRateDto>
            privatBankExchangeRateDTOGetDataFromApiService;

    private final GetDataFromApiService<MinFinExchangeRateDto>
            minFinExchangeRateDTOGetDataFromApiService;

    private final AbstractParser<PrivatBankExchangeRateDto, ExchangeRate> privatParser;

    private final AbstractParser<MonoExchangeRateDto, ExchangeRate> monoParser;

    private final AbstractParser<MinFinExchangeRateDto, ExchangeRate> minFinParser;

    @Scheduled(cron = "0 */3 * ? * *")
    public void job() {
        MonoExchangeRateDto[] monoList = monoExchangeRateDtoGetDataFromApiService
                .getData(MONO_LINK, MonoExchangeRateDto[].class);
        monoExchangeRateDtoGetDataFromApiService.saveData(monoList, MONO_NAME, monoParser);
        PrivatBankExchangeRateDto[] privatList = privatBankExchangeRateDTOGetDataFromApiService
                .getData(PRIVAT_LINK, PrivatBankExchangeRateDto[].class);
        privatBankExchangeRateDTOGetDataFromApiService
                .saveData(privatList, PRIVAT_NAME, privatParser);
        MinFinExchangeRateDto[] minFinList = minFinExchangeRateDTOGetDataFromApiService
                .getData(MINFIN_LINK, MinFinExchangeRateDto[].class);
        minFinExchangeRateDTOGetDataFromApiService.saveData(minFinList, MINFIN_NAME, minFinParser);
    }
}
