package pl.parser.nbp;

import pl.parser.nbp.helpers.ResultCalculator;
import pl.parser.nbp.helpers.XMLHelper;
import pl.parser.nbp.helpers.FileHelper;

import pl.parser.nbp.xmlModels.ExchangeRatesTable;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class MainClass {

  public static void main(String[] args) {

    Long start = System.nanoTime();

    FileHelper fileHelper = new FileHelper();

    LocalDate dateRangeFrom = LocalDate.parse(args[1]);
    LocalDate dateRangeTo = LocalDate.parse(args[2]);

    Map<Long, List<String>> filesByYear = fileHelper.getFilesToDownload(dateRangeFrom, dateRangeTo);
    XMLHelper xmlHelper = new XMLHelper();

    ResultCalculator resCalc = new ResultCalculator();
    try {
      List<ExchangeRatesTable> exchangeRatesTableByCurrency = xmlHelper.downloadXMLData(filesByYear);

      BigDecimal mean = resCalc.calculateResult(exchangeRatesTableByCurrency, args[0]);
      System.out.println(mean);
    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println((double) (System.nanoTime() - start) / 1_000_000_000.0);

  }

//  private static void validateCommandLineArgs (String[] args) {
//
//  }
}
