package com.place_application.demo.interceptor;

import com.place_application.demo.config.JwtConfig;
import com.place_application.demo.utils.HttpUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;
import org.apache.ibatis.plugin.Intercepts;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TeacherInterceptor implements HandlerInterceptor {
    private JwtConfig jwtConfig ;
    public TeacherInterceptor() {
        jwtConfig = new JwtConfig();
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

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
