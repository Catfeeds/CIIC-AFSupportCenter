package com.ciicsh.gto.afsupportcenter.util.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.ciicsh.gto.afsupportcenter.util.web.request.WafRequestWrapper;

/**
 * Filter 请求过滤
 */
public class WafFilter implements Filter {
  private FilterConfig filterConfig = null;

  private boolean filterXSS = true;
  private boolean filterSQL = true;

  public WafFilter() {

  }

  public WafFilter(boolean filterXSS, boolean filterSQL) {
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

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    this.filterConfig = filterConfig;
  }

  @Override
  public void destroy() {
    this.filterConfig = null;
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    chain.doFilter(new WafRequestWrapper((HttpServletRequest) request, filterXSS, filterSQL), response);
  }
}
