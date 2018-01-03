package com.ciicsh.gto.afsupportcenter.util.json.jackson2;

import java.io.IOException;

import com.ciicsh.gto.afsupportcenter.util.kit.WafKit;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class WafJsonSerializer extends JsonSerializer<String> {

  private boolean filterXSS = true;
  private boolean filterSQL = true;

  public WafJsonSerializer() {}

  public WafJsonSerializer(boolean filterXSS, boolean filterSQL) {
    this.filterXSS = filterXSS;
    this.filterSQL = filterSQL;
  }

  public boolean isFilterXSS() {
    return filterXSS;
  }

  public void setFilterXSS(boolean filterXSS) {
    this.filterXSS = filterXSS;
  }

  public boolean isFilterSQL() {
    return filterSQL;
  }

  public void setFilterSQL(boolean filterSQL) {
    this.filterSQL = filterSQL;
  }

  public Class<String> handledType() {
    return String.class;
  }

  public void serialize(String value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
      throws IOException {
    if (value != null) {
      // 过滤字符串内容
      String tmpStr = value;
      if (this.filterXSS) {
        tmpStr = WafKit.stripXSS(tmpStr);
      }
      if (this.filterSQL) {
        tmpStr = WafKit.stripSqlInjection(tmpStr);
      }
      jsonGenerator.writeString(tmpStr);
    }
  }
}
