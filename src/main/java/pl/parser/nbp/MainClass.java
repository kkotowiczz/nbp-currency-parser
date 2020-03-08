package pl.parser.nbp;

import pl.parser.nbp.helpers.ResultCalculator;
import pl.parser.nbp.helpers.XMLHelper;
import pl.parser.nbp.helpers.FileHelper;
import pl.parser.nbp.xmlModels.ExchangeRatesSeries;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;



public class MainClass {

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    LocalDate dateRangeFrom = LocalDate.parse(args[1]);
    LocalDate dateRangeTo = LocalDate.parse(args[2]);

    Map<Integer, List<LocalDate>> dateRange = FileHelper.getFilesToDownload(dateRangeFrom, dateRangeTo);

    try {
      List<ExchangeRatesSeries> ratesSeries = XMLHelper.downloadXMLData(dateRange, args[0]);
      System.out.println(ResultCalculator.calculateResult(ratesSeries));
      System.out.println(System.currentTimeMillis() - start);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
