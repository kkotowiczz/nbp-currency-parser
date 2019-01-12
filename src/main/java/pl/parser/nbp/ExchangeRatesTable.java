package pl.parser.nbp;



import java.util.List;


public class ExchangeRatesTable {

  private String tableNumber;
  private String recordDate;
  private String publicationDate;


  private List<Item> itemList;
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