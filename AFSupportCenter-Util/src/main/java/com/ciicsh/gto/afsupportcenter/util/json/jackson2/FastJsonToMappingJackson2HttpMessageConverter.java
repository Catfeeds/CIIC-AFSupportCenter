package com.ciicsh.gto.afsupportcenter.util.json.jackson2;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

/**
 * 用 FastJson 重新 read 方法，jackson2 对有多余的参数会报 400
 */
public class FastJsonToMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {

    private final FastJsonConfig fastJsonConfig;

    public FastJsonToMappingJackson2HttpMessageConverter(FastJsonConfig fastJsonConfig) {
        this.fastJsonConfig = fastJsonConfig;
    }

    /**
     * 重新 read 方法，jackson2 对有多余的参数会报 400
     *
     * @param type
     * @param contextClass
     * @param inputMessage
     * @return
     * @throws IOException
     * @throws HttpMessageNotReadableException
     */
    @Override
    public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        InputStream in = inputMessage.getBody();
        return JSON.parseObject(in, fastJsonConfig.getCharset(), type, fastJsonConfig.getFeatures());
    }
}