package com.ciicsh.gto.afsupportcenter.util.client;

import com.ciicsh.gto.afsupportcenter.util.exception.BusinessException;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * EntityIdHolder 工具箱
 */
public class EntityIdHolder implements ApplicationContextAware {

    private static RestTemplate restTemplate;
    private static String url;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        RestTemplate restTemplate = applicationContext.getBean(RestTemplate.class);
        BusinessException.notNull(restTemplate, "RestTemplate 属性为 null ，请检查是否初始化 RestTemplate ！");
        EntityIdHolder.restTemplate = restTemplate;
        EntityIdHolder.url = "http://service-basicdataservice/api/basicdataservice/entityid/getEntityIds";
    }

    /**
     * 业务实体编号生成
     *
     * @param id
     * @return
     */
    public static String genId(String id) {
        return genId(restTemplate, url, id);
    }

    public static String genId(RestTemplate restTemplate, String url, String id) {
        Map<String, Object> request = new HashMap<>();
        request.put("categoryId", id);
        request.put("number", 1);

        JsonResult<List<String>> tip = restTemplate.getForObject(url + "?categoryId={categoryId}&number={number}", JsonResult.class, request);

        if (tip.getCode() == 200) {
            List<String> data = tip.getData();
            return data.get(0);
        }
        throw new BusinessException(tip.getMessage());
    }

}
