package pl.parser.nbp.helpers;

import pl.parser.nbp.xmlModels.ExchangeRatesTable;
import pl.parser.nbp.xmlModels.Item;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import java.util.stream.Collectors;

public class ResultCalculator {
  public static BigDecimal calculateResult(List<ExchangeRatesTable> currencyRates, String currencyCode) {
    return calculateMeanAverage(currencyRates, currencyCode);
  }

  private static BigDecimal calculateMeanAverage(List<ExchangeRatesTable> currencyRates, String currencyCode) {
    BigDecimal length = BigDecimal.valueOf(currencyRates.size());

    List<BigDecimal> items = currencyRates.stream()
      .map(rates -> rates.getItemList())
      .flatMap(item -> item.stream()
        .filter(buyingRate -> buyingRate.getCurrencyCode().equals(currencyCode))
        .map(buyingRate -> buyingRate.getBuyingRate())).collect(Collectors.toList());

    BigDecimal result = items.stream().reduce(BigDecimal.ZERO, BigDecimal::add);


    return result.divide(length, 4, RoundingMode.HALF_UP);
  }
}
