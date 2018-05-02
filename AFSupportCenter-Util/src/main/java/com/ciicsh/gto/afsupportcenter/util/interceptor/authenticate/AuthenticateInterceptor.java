package com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate;

import com.ciicsh.gto.identityservice.api.IdentityServiceProxy;
import com.ciicsh.gto.identityservice.api.dto.Result;
import com.ciicsh.gto.identityservice.api.dto.response.UserInfoResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.security.auth.message.AuthException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Stream;

/**
 * 用户验证拦截器
 * @author linhaihai
 */
public class AuthenticateInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private IdentityServiceProxy identityServiceProxy;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String headers = request.getHeader("Access-Control-Request-Headers");
        if (headers == null || Stream.of(headers.split(",")).noneMatch(header ->
                header.equalsIgnoreCase("token"))) {
            String token = request.getHeader("token");
            if (StringUtils.isEmpty(token)) {
                token = request.getParameter("token");
            }
            if (StringUtils.hasText(token)) {
                Result<UserInfoResponseDTO> result = identityServiceProxy.getUserInfoByToken(token);
                if (result.getCode() == 0) {
                    UserContext.setUser(result.getObject());
                } else {
                    addHeader(request, response);
                    throw new AuthException("token is not valid");
                }
            } else {
                addHeader(request, response);
                throw new AuthException("token is null");
            }
        }
        return true;
    }

    private void addHeader(HttpServletRequest request, HttpServletResponse response) {
        if (request.getHeader(HttpHeaders.ORIGIN) != null) {
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Allow-Credentials", "true");
            response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT, HEAD");
            response.addHeader("Access-Control-Allow-Headers", "Content-Type");
            response.addHeader("Access-Control-Max-Age", "3600");
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
        UserContext.remove();
    }

}
