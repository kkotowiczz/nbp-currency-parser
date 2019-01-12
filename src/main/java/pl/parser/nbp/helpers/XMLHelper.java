package pl.parser.nbp.helpers;

import org.apache.http.HttpHeaders;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClients;
import pl.parser.nbp.xmlModels.ExchangeRatesTable;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class XMLHelper {
  public static List<ExchangeRatesTable> downloadXMLData(Map<Long, List<String>> filesByYear) throws IOException {

    HttpClient client = HttpClients.custom().build();
    List<ExchangeRatesTable> list = new ArrayList<>();

    filesByYear.entrySet().stream()
      .forEach(year -> year.getValue().forEach(fileName -> {
        HttpUriRequest request = RequestBuilder.get()
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

//  public static String inputStreamToString(InputStream is) throws IOException {
//    StringBuilder sb = new StringBuilder();
//    String line;
//    BufferedReader br = new BufferedReader(new InputStreamReader(is));
//    while ((line = br.readLine()) != null) {
//      sb.append(line);
//    }
//    br.close();
//    return sb.toString();
//  }

  private static final ExchangeRatesTable unmarshall(InputStream is) throws JAXBException, IOException {
    JAXBContext context = JAXBContext.newInstance(ExchangeRatesTable.class);
    return (ExchangeRatesTable) context.createUnmarshaller().unmarshal(is);
  }
}
