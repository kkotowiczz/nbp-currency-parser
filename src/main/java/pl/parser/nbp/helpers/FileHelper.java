package pl.parser.nbp.helpers;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class FileHelper {

  public static final Map<Long, List<String>> getFilesToDownload(LocalDate a1, LocalDate a2) {
    List<String> dataURLS = createURLsFromDates(a1, a2);
    Map<Long, List<String>> filesMap = new HashMap<>();


    String startingCondition = prepareFileNameRanges(a1.getYear(), a1.getMonthValue(), a1.getDayOfMonth());
    String endingCondition = prepareFileNameRanges(a2.getYear(), a2.getMonthValue(), a2.getDayOfMonth());


    dataURLS.forEach(url -> {
      try (InputStream is = new URL(url).openStream();
           BufferedReader reader = new BufferedReader(new InputStreamReader(is));
           Stream<String> stream = reader.lines()) {
        String extractedYearFromFilename = url.substring(url.indexOf("/dir") + 4, url.indexOf(".txt"));
        extractedYearFromFilename = "".equals(extractedYearFromFilename) ? String.valueOf(LocalDate.now().getYear()) : extractedYearFromFilename;
        Long yearKeyValue = Long.valueOf(extractedYearFromFilename);

        // startsWith changed for contains because of first file name starting with zero-width space
        List<String> filesList = stream.filter(file -> file.contains("c") &&
          file.substring(file.length() - 6).compareTo(startingCondition) >= 0 && file.substring(file.length() - 6).compareTo(endingCondition) <= 0)
          .collect(Collectors.toList());

        filesMap.put(yearKeyValue, filesList);
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
    return filesMap;
  }

  private static final List<String> createURLsFromDates(LocalDate yearRangeFrom, LocalDate yearRangeTo) {
    String baseUrl = "https://www.nbp.pl/kursy/xml/";
    List<String> urls = new ArrayList<>();


    if (yearRangeFrom.getYear() == yearRangeTo.getYear()) {
      String sameYearRangeURL = LocalDate.now().getYear() == yearRangeFrom.getYear() ?
        baseUrl + "dir.txt" : baseUrl + "dir" + yearRangeFrom.getYear() + ".txt";
      urls.add(sameYearRangeURL);
    } else if (yearRangeFrom.getYear() < yearRangeTo.getYear() && yearRangeTo.getYear() <= LocalDate.now().getYear()) {
      IntStream.rangeClosed(yearRangeFrom.getYear(), yearRangeTo.getYear())
        .forEach(year -> urls.add(baseUrl + "dir" + (year != LocalDate.now().getYear() ?  year : "") + ".txt"));
    } else
        throw new IllegalArgumentException("Invalid year passed as argument");

    return urls;
  }

  private static final String correctDateValue(int dateValue) {
    return Integer.toString(dateValue).length() == 1 ? "0" + dateValue : Integer.toString(dateValue);
  }

  private static final String prepareFileNameRanges(int year, int month, int day) {
    return Integer.toString(year).substring(2) + correctDateValue(month) + correctDateValue(day);
  }

}
