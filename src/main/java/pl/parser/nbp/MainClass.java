package pl.parser.nbp;

import pl.parser.nbp.helpers.ResultCalculator;
import pl.parser.nbp.helpers.XMLHelper;
import pl.parser.nbp.helpers.FileHelper;

import pl.parser.nbp.xmlModels.ExchangeRatesTable;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;



public class MainClass {

  public static void main(String[] args) {
    LocalDate dateRangeFrom = LocalDate.parse(args[1]);
    LocalDate dateRangeTo = LocalDate.parse(args[2]);

    Map<Long, List<String>> filesByYear = FileHelper.getFilesToDownload(dateRangeFrom, dateRangeTo);

    ResultCalculator resCalc = new ResultCalculator();
    try {
      long x = System.currentTimeMillis();
      List<ExchangeRatesTable> exchangeRatesTableByCurrency = XMLHelper.downloadXMLData(filesByYear);
      System.out.println("actual requests  " + (System.currentTimeMillis() - x));
      long z = System.currentTimeMillis();
      String finalResult = ResultCalculator.calculateResult(exchangeRatesTableByCurrency, args[0]);
      System.out.println("calculations " + (System.currentTimeMillis() - z));
      System.out.println(finalResult);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
