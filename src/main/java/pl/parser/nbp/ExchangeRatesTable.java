package pl.parser.nbp;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "tabela_kursow")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExchangeRatesTable {

  @XmlElement(name = "numer_tabeli")
  private String tableNumber;
  @XmlElement(name = "data_notowania")
  private String recordDate;
  @XmlElement(name = "data_publikacji")
  private String publicationDate;
  @XmlElement(name = "pozycja")
  private List<Item> itemList;

  public ExchangeRatesTable() {}

  public ExchangeRatesTable(String tableNumber, String recordDate, String publicationDate, List<Item> itemList) {
    this.tableNumber = tableNumber;
    this.recordDate = recordDate;
    this.publicationDate = publicationDate;
    this.itemList = itemList;
  }

  public String getTableNumber() {
    return tableNumber;
  }

  public void setTableNumber(String tableNumber) {
    this.tableNumber = tableNumber;
  }

  public String getRecordDate() {
    return recordDate;
  }

  public void setRecordDate(String recordDate) {
    this.recordDate = recordDate;
  }

  public String getPublicationDate() {
    return publicationDate;
  }

  public void setPublicationDate(String publicationDate) {
    this.publicationDate = publicationDate;
  }

  public List<Item> getItemList() {
    return itemList;
  }

  public void setItemList(List<Item> itemList) {
    this.itemList = itemList;
  }
}