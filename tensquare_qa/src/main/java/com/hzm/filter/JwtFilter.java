package com.hzm.filter;

import com.hzm.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtFilter extends HandlerInterceptorAdapter {
    @Autowired
    private JwtUtil jwtUtil;

    public boolean prehandle(HttpServletRequest request, HttpServletResponse response,Object handler)throws Exception{
        System.out.println("经过了过滤器");
        final String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer ")){
            final String token = authorization.substring(7);
            Claims claims = jwtUtil.parseJwt(token);
            if ("admin".equals(claims.get("roles"))){
                request.setAttribute("admin_claim",claims);
            }else if ("user".equals(claims.get("roles"))){
                request.setAttribute("user_claim",claims);
            }
        }
        return true;
    }
}
