package com.ciicsh.gto.afsupportcenter.util.web.request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 需要实例化 RequestContextListener
 */
public class RequestHolder {

  public static HttpServletRequest getRequest() {
    HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    return req;
  }

  public static HttpServletResponse getResponse() {
    HttpServletResponse resp = ((ServletWebRequest) RequestContextHolder.getRequestAttributes()).getResponse();
    return resp;
  }
}
