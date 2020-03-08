package pl.parser.nbp.helpers;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class FileHelper {

  public static Map<Integer, List<LocalDate>> getFilesToDownload(LocalDate from, LocalDate to) {
    return Stream.iterate(from, date -> date.plusDays(1))
      .limit(ChronoUnit.DAYS.between(from, to.plusDays(1)))
      .collect(Collectors.groupingBy((LocalDate z) -> z.getYear()));
  }

}
