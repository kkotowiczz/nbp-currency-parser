package pl.parser.nbp.helpers;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import pl.parser.nbp.xmlModels.ExchangeRatesSeries;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class XMLHelper {

  private static final CloseableHttpClient httpClient = HttpClients.createDefault();

  public static List<ExchangeRatesSeries> downloadXMLData(Map<Integer, List<LocalDate>> files, String currency) throws IOException, ClientProtocolException {
    return files.values().stream().map(dates -> {
      String url  = "http://api.nbp.pl/api/exchangerates/rates/c/" + currency.toLowerCase()
        + "/" + dates.get(0) + "/" + dates.get(dates.size() - 1) + "/?format=xml";
        System.out.println(url);
        HttpGet request = new HttpGet(url);

        try (CloseableHttpResponse response = httpClient.execute(request)) {
          String xmlString = EntityUtils.toString(response.getEntity(), Charset.defaultCharset());
          JAXBContext cx = JAXBContext.newInstance(ExchangeRatesSeries.class);
          Unmarshaller jaxbUnmarshaller = cx.createUnmarshaller();
          return (ExchangeRatesSeries) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));

        } catch (Exception e) {
          e.printStackTrace();
          return null;
        }
      }).collect(Collectors.toList());
  }
}

