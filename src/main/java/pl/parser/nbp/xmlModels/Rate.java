package pl.parser.nbp.xmlModels;

import pl.parser.nbp.helpers.BigDecimalAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Rate")
public class Rate {
  @XmlElement(name = "No")
  private String no = "";

  @XmlElement(name = "EffectiveDate")
  private String effectiveDate = "";

  @XmlElement(name = "Ask")
  private BigDecimal ask = BigDecimal.ZERO;

  @XmlElement(name = "Bid")
  private BigDecimal bid = BigDecimal.ZERO;

  public Rate() {}

  public Rate(String no, String effectiveDate, BigDecimal ask, BigDecimal bid) {
    this.no = no;
    this.effectiveDate = effectiveDate;
    this.ask = ask;
    this.bid = bid;
  }

  public String getNo() {
    return no;
  }

  public void setNo(String no) {
    this.no = no;
  }

  public String getEffectiveDate() {
    return effectiveDate;
  }

  public void setEffectiveDate(String effectiveDate) {
    this.effectiveDate = effectiveDate;
  }

  public BigDecimal getAsk() {
    return ask;
  }

  public void setAsk(BigDecimal ask) {
    this.ask = ask;
  }

  public BigDecimal getBid() {
    return bid;
  }

  public void setBid(BigDecimal bid) {
    this.bid = bid;
  }
}
