package pl.parser.nbp.helpers;

import org.apache.http.HttpHeaders;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClients;
import pl.parser.nbp.xmlModels.ExchangeRatesTable;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public final class XMLHelper {
  public static List<ExchangeRatesTable> downloadXMLData(Map<Long, List<String>> filesByYear) throws IOException {

    HttpClient client = HttpClients.custom().build();
    List<ExchangeRatesTable> list = new ArrayList<>();

    filesByYear.entrySet().stream()
      .forEach(year -> year.getValue().forEach(fileName -> {
        HttpUriRequest request = RequestBuilder.get()
          // replacing zero width space
          .setUri("http://www.nbp.pl/kursy/xml/" + fileName.replace("\uFEFF", "") + ".xml")
          .setHeader(HttpHeaders.CONTENT_TYPE, "text/xml")
          .build();

        try {
          list.add(unmarshall(client.execute(request).getEntity().getContent()));
        } catch (Exception e) {
          e.printStackTrace();
        }

      }));

    return list;
  }


  private static final ExchangeRatesTable unmarshall(InputStream is) throws JAXBException, IOException {
    JAXBContext context = JAXBContext.newInstance(ExchangeRatesTable.class);
    return (ExchangeRatesTable) context.createUnmarshaller().unmarshal(is);
  }
}
