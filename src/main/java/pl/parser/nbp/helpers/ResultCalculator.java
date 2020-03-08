package pl.parser.nbp.helpers;

import pl.parser.nbp.xmlModels.ExchangeRatesSeries;
import pl.parser.nbp.xmlModels.Rate;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

import java.util.stream.Collectors;

public class ResultCalculator {
  public static String calculateResult(List<ExchangeRatesSeries> currencyRates) {
    List<BigDecimal> buyingRates = currencyRates.stream()
      .flatMap(rates -> rates.getRates().stream().map(Rate::getBid)).collect(Collectors.toList());

    BigDecimal meanAverage = calculateMeanAverage(buyingRates);
    BigDecimal standardDeviation = calculateStandardDeviation(buyingRates, meanAverage);

    return meanAverage.toString() + " - średnia arytmetyczna " + "\n" + standardDeviation.toString() + " - odchylenie standardowe";
  }

  private static BigDecimal calculateMeanAverage(List<BigDecimal> buyingRates) {
    BigDecimal length = BigDecimal.valueOf(buyingRates.size());
    BigDecimal result = buyingRates.stream().reduce(BigDecimal.ZERO, BigDecimal::add);

    return result.divide(length, 4, RoundingMode.HALF_UP);
  }

  private static BigDecimal calculateStandardDeviation(List<BigDecimal> buyingRates, BigDecimal meanValue) {
    MathContext mc = new MathContext(3);
    BigDecimal length = BigDecimal.valueOf(buyingRates.size());

    BigDecimal standardDeviation = buyingRates.stream()
      .reduce(BigDecimal.ZERO, (acc, currVal) -> acc.add(currVal.subtract(meanValue).pow(2)));

    BigDecimal q = standardDeviation.divide(length, mc);

    return new BigDecimal(Math.sqrt(q.doubleValue()), mc);
  }
}
