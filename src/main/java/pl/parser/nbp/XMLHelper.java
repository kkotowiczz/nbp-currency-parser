package pl.parser.nbp;

import org.apache.http.HttpHeaders;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class XMLHelper {
  public static String downloadXMLData(Map<Long, List<String>> filesByYear) throws IOException {

    HttpClient client = HttpClients.custom().build();

    filesByYear.entrySet().stream()
      .forEach(s -> s.getValue().forEach(sz -> {
        HttpUriRequest request = RequestBuilder.get()
          .setUri("http://www.nbp.pl/kursy/xml/" + sz.replace("\uFEFF", "") + ".xml")
          .setHeader(HttpHeaders.CONTENT_TYPE, "text/xml")
          .build();

        List<ExchangeRatesTable> list = new ArrayList<>();
      }));

    return "zz";
  }

  public static String inputStreamToString(InputStream is) throws IOException {
    StringBuilder sb = new StringBuilder();
    String line;
    BufferedReader br = new BufferedReader(new InputStreamReader(is));
    while ((line = br.readLine()) != null) {
      sb.append(line);
    }
    br.close();
    return sb.toString();
  }
}
