package ua.lipenets.currency_exchange.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.lipenets.currency_exchange.model.AverageRate;
import ua.lipenets.currency_exchange.service.FindAverageService;

@RestController
@RequestMapping("/currency")
@RequiredArgsConstructor
public class ExchangeRateController {
    private final FindAverageService findAverageService;

    @GetMapping("/average")
    @ApiOperation("Returns a list of average rates for all sources")
    public List<AverageRate> getRatesWithAverageValue() {
        return findAverageService.getAverageCourses();
    }

    @GetMapping("/average_by_date")
    @ApiOperation("Returns a list of average rates for every currency for the period")
    public List<AverageRate> getRatesForAllSourcesForPeriod(@ApiParam("'yyyy-MM-dd'")
                                                            @RequestParam String from,
                                                             @ApiParam("'yyyy-MM-dd'")
                                                             @RequestParam String to) {

        return findAverageService.getAverageCoursesByPeriod(from, to);
    }
}
