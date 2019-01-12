package pl.parser.nbp.helpers;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.math.BigDecimal;

public class BigDecimalAdapter extends XmlAdapter<String, BigDecimal> {
  @Override
  public BigDecimal unmarshal(String v) throws Exception {
    return new BigDecimal(v.replaceAll(",", "."));
  }

  @Override
  public String marshal(BigDecimal value) {
    return value.toString();
  }
}
