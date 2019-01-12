package pl.parser.nbp;

public class Item {

  private String currencyName;
  private Long converter;
  private String currencyCode;
  private String buyingRate;
  private String sellingRate;


  public Item(String currencyName, Long converter, String currencyCode, String buyingRate, String sellingRate) {
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

  public String getBuyingRate() {
    return buyingRate;
  }

  public void setBuyingRate(String buyingRate) {
    this.buyingRate = buyingRate;
  }

  public String getSellingRate() {
    return sellingRate;
  }

  public void setSellingRate(String sellingRate) {
    this.sellingRate = sellingRate;
  }

}

