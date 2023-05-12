package ua.lipenets.currency_exchange.service;

import java.util.List;
import ua.lipenets.currency_exchange.model.AverageRate;

public interface FindAverageService {
    List<AverageRate> getAverageCourses();

    List<AverageRate> getAverageCoursesByPeriod(String from, String to);
}
