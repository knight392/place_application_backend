package com.place_application.demo.interceptor;

import com.place_application.demo.config.JwtConfig;
import com.place_application.demo.utils.HttpUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;
import org.springframework.util.StringUtils;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 管理员端拦截器，只有登录后才能访问
 */
@Component
public class AdminInterceptor implements HandlerInterceptor {
    private JwtConfig jwtConfig ;
    public AdminInterceptor() {
        jwtConfig = new JwtConfig();
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle");
        /** Token 验证 */
        String token = HttpUtil.getTokenFromCookie(jwtConfig,request);
        Claims claims = null;
        try{
            claims = jwtConfig.getTokenClaim(token);
            if(claims == null || jwtConfig.isTokenExpired(claims.getExpiration())){
                throw new SignatureException(jwtConfig.getHeader() + "失效，请重新登录。");
            }
        }catch (Exception e){
            throw new SignatureException(jwtConfig.getHeader() + "失效，请重新登录。");
        }
        return true;
}
}
