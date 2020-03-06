package pl.parser.nbp.helpers;

import org.apache.http.HttpHeaders;
import org.apache.http.client.ClientProtocolException;
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
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class XMLHelper {

  public static List<ExchangeRatesTable> downloadXMLData(Map<Long, List<String>> filesByYear) throws IOException, ClientProtocolException {

    HttpClient client = HttpClients.custom().build();
    List<ExchangeRatesTable> list = new ArrayList<>();
    Executor ex = createExecutor(filesByYear);
    List<CompletableFuture<ExchangeRatesTable>> completableFutureList = filesByYear.values().stream().flatMap(urls ->
      urls.stream().map(url ->
        CompletableFuture.supplyAsync(() -> {
          HttpUriRequest request = RequestBuilder.get()
            // replacing zero width space
            .setUri("http://www.nbp.pl/kursy/xml/" + url.replace("\uFEFF", "") + ".xml")
            .setHeader(HttpHeaders.CONTENT_TYPE, "text/xml")
            .build();

          return requestData(client, request);

        }, ex ))).collect(Collectors.toList());

    completableFutureList.forEach(System.out::println);

    System.out.println(completableFutureList.size() + java.lang.Thread.activeCount());

    return  completableFutureList.stream().map(CompletableFuture::join).collect(Collectors.toList());

  }


  private static ExchangeRatesTable unmarshall(InputStream is) throws JAXBException, IOException {
    JAXBContext context = JAXBContext.newInstance(ExchangeRatesTable.class);
    return (ExchangeRatesTable) context.createUnmarshaller().unmarshal(is);
  }

  private static ExchangeRatesTable requestData (HttpClient client, HttpUriRequest request) {
    try {
      return unmarshall(client.execute(request).getEntity().getContent());
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;

  }

  private static Executor createExecutor(Map<Long, List<String>> files) {
    int pool = files.values().stream().mapToInt(x -> x.size()).sum();
    return Executors.newFixedThreadPool(pool, (Runnable r) -> {
      Thread t = new Thread(r);
      t.setDaemon(true);
      return t;
    });
  }
}
