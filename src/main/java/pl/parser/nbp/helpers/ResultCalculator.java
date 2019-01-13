package pl.parser.nbp.helpers;

import pl.parser.nbp.xmlModels.ExchangeRatesTable;
import pl.parser.nbp.xmlModels.Item;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import java.util.stream.Collectors;

public class ResultCalculator {
  public static BigDecimal calculateResult(List<ExchangeRatesTable> currencyRates, String currencyCode) {
    List<BigDecimal> buyingRates = currencyRates.stream()
      .map(rates -> rates.getItemList())
      .flatMap(item -> item.stream()
        .filter(buyingRate -> buyingRate.getCurrencyCode().equals(currencyCode))
        .map(buyingRate -> buyingRate.getBuyingRate())).collect(Collectors.toList());

    BigDecimal meanAverage = calculateMeanAverage(buyingRates);
    BigDecimal stdDev = calculateStandardDevation(buyingRates, meanAverage);

    System.out.println(stdDev);

    return meanAverage;
  }

  private static BigDecimal calculateMeanAverage(List<BigDecimal> buyingRates) {
    BigDecimal length = BigDecimal.valueOf(buyingRates.size());
    BigDecimal result = buyingRates.stream().reduce(BigDecimal.ZERO, BigDecimal::add);

    return result.divide(length, 4, RoundingMode.HALF_UP);
  }

  private static BigDecimal calculateStandardDevation(List<BigDecimal> buyingRates, BigDecimal meanValue) {
    MathContext mc = new MathContext(4);
    BigDecimal length = BigDecimal.valueOf(buyingRates.size());

    BigDecimal standardDeviation = buyingRates.stream()
      .reduce(BigDecimal.ZERO, (acc, currVal) -> {
        BigDecimal z = currVal.subtract(meanValue);
        BigDecimal x =  z.pow(2);
        return acc.add(x);
      });
    System.out.println(standardDeviation);
    BigDecimal q = standardDeviation.divide(length, 6);

    return new BigDecimal(Math.sqrt(q.doubleValue()));
  }
}
