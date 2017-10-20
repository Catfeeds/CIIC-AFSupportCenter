package com.ciicsh.gto.afsupportcenter.generator.mybatis;

import org.mybatis.generator.api.IntrospectedColumn;

/**
 * 自定义 IntrospectedColumn，如: DataChange_LastTime 生成 dataChangeLastTime 而不是 datachangeLasttime
 * 注：需要在 table 中配置 <property name="useActualColumnNames" value="true"/> 联合使用
 */
public class CustomIntrospectedColumn extends IntrospectedColumn {

  @Override
  public void setJavaProperty(String javaProperty) {
    super.setJavaProperty(javaProperty.replaceAll("_", ""));
  }

  public static void main(String... args) {
    System.out.println("asdf_Bd_Adf".replaceAll("_", ""));
  }
}
