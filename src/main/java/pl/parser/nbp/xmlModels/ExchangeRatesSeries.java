package pl.parser.nbp.xmlModels;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "ExchangeRatesSeries")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExchangeRatesSeries {
  @XmlElement(name = "Table")
  private String table = "";
  @XmlElement(name = "Currency")
  private String currency = "";
  @XmlElement(name =  "Code")
  private String code = "";
  @XmlElement(name = "Rate")
  @XmlElementWrapper(name = "Rates")
  private List<Rate> rates;

  public ExchangeRatesSeries() {}

  public ExchangeRatesSeries(String table, String currency, String code, List<Rate> rates) {
    this.table = table;
    this.currency = currency;
    this.code = code;
    this.rates = rates;
  }

  public String getTable() {
    return table;
  }

  public void setTable(String table) {
    this.table = table;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public List<Rate> getRates() {
    return rates;
  }

  public void setRates(List<Rate> rates) {
    this.rates = rates;
  }

  @Override
  public String toString() {
    return "ExchangeRatesSeries{" +
      "table='" + table + '\'' +
      ", currency='" + currency + '\'' +
      ", code='" + code + '\'' +
      '}';
  }
}
