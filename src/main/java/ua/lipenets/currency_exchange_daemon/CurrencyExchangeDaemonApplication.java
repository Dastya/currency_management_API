package ua.lipenets.currency_exchange_daemon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CurrencyExchangeDaemonApplication {
    public static void main(String[] args) {
        SpringApplication.run(CurrencyExchangeDaemonApplication.class, args);
    }

}
