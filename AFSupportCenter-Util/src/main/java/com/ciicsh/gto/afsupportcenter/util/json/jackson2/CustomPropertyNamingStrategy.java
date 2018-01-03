package com.ciicsh.gto.afsupportcenter.util.json.jackson2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import org.springframework.util.ReflectionUtils;

/**
 * 自定义 PropertyNamingStrategy，用于特殊处理 属性名称
 */
public class CustomPropertyNamingStrategy extends PropertyNamingStrategy {

  // 反序列化时调用
  @Override
  public String nameForSetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
    return getName(method, defaultName);
  }

  // 序列化时调用
  @Override
  public String nameForGetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
    return getName(method, defaultName);
  }

  private String getName(AnnotatedMethod method, String defaultName) {

    String name = method.getName().substring(method.getName().length() - defaultName.length());
    Named named = new Named(defaultName);

    ReflectionUtils.doWithFields(method.getAnnotated().getDeclaringClass(), (f) -> {
      String fieldName = f.getName();
      // 查找对应的 Field ，如：SSPolicyId
      if (fieldName.equals(name)) {
        named.setValue(fieldName);
        return;
      }
    });
    return named.getValue();
  }

  private static final class Named {
    public String value;

    public Named(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    public void setValue(String value) {
      this.value = value;
    }
  }


  public static void main(String... args) {
    ObjectMapper mapper = new ObjectMapper();
    mapper.setPropertyNamingStrategy(new CustomPropertyNamingStrategy());

    TestPOJO pojo = new TestPOJO();
    pojo.setSSPolicyId("test");
    pojo.setAge(18);
    pojo.setName("张三");
    try {
      System.out.println(mapper.writeValueAsString(pojo));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }


  static class TestPOJOParent {
    private String SSPolicyId;

    public String getSSPolicyId() {
      return SSPolicyId;
    }

    public void setSSPolicyId(String SSPolicyId) {
      this.SSPolicyId = SSPolicyId;
    }
  }


  static class TestPOJO extends TestPOJOParent {

    private int age;
    private String name;
    private boolean sex;

    public int getAge() {
      return age;
    }

    public void setAge(int age) {
      this.age = age;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public boolean isSex() {
      return sex;
    }

    public void setSex(boolean sex) {
      this.sex = sex;
    }
  }
}
