package com.ciicsh.gto.afsupportcenter.test;

import com.alibaba.fastjson.JSON;
import com.ciicsh.gto.afsupportcenter.util.client.EntityIdHolder;
import org.springframework.web.client.RestTemplate;

/**
 * EntityId Test
 */
public class EntityIdTest {

  public static void main(String... args) {
    RestTemplate template = new RestTemplate();
    String url = "http://localhost:8083/api/basicdataservice/entityid/genId";
    String id = "EC0009";
    System.out.println(JSON.toJSONString(EntityIdHolder.genId(template, url, id)));
  }
}
