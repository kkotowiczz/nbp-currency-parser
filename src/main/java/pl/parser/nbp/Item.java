package pl.parser.nbp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "pozycja")
public class Item {

  @XmlElement(name = "nazwa_waluty")
  private String currencyName;
  @XmlElement(name = "przelicznik")
  private Long converter;
  @XmlElement(name = "kod_waluty")
  private String currencyCode;
  @XmlElement(name = "kurs_kupna")
  @XmlJavaTypeAdapter(BigDecimalAdapter.class)
  private BigDecimal buyingRate;
  @XmlElement(name = "kurs_sprzedazy")
  @XmlJavaTypeAdapter(BigDecimalAdapter.class)
  private BigDecimal sellingRate;

  public Item() {}


  public Item(String currencyName, Long converter, String currencyCode, BigDecimal buyingRate, BigDecimal sellingRate) {
    this.currencyName = currencyName;
    this.converter = converter;
    this.currencyCode = currencyCode;
    this.buyingRate = buyingRate;
    this.sellingRate = sellingRate;
  }

  public String getCurrencyName() {
    return currencyName;
  }

  public void setCurrencyName(String currencyName) {
    this.currencyName = currencyName;
  }

  public Long getConverter() {
    return converter;
  }

  public void setConverter(Long converter) {
    this.converter = converter;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
  }

  public BigDecimal getBuyingRate() {
    return buyingRate;
  }

  public void setBuyingRate(BigDecimal buyingRate) {
    this.buyingRate = buyingRate;
  }

  public BigDecimal getSellingRate() {
    return sellingRate;
  }

  public void setSellingRate(BigDecimal sellingRate) {
    this.sellingRate = sellingRate;
  }

}

