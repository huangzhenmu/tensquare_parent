package com.hzm.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@ConfigurationProperties("jwt.config") //将配置映射到类
public class JwtUtil {
    private String key;
    private Long ttl;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getTtl() {
        return ttl;
    }

    public void setTtl(Long ttl) {
        this.ttl = ttl;
    }

    /**
     * 生成token
     * @param id
     * @param subject
     * @param roles
     * @return
     */
    public String createJwt(String id, String subject, String roles){
        Long nowMillis = System.currentTimeMillis();
        Date date = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(date)
                .setSubject(subject)
                .signWith(SignatureAlgorithm.HS256,key)
                .claim("roles",roles);
        if (ttl > 0){
            builder.setExpiration(new Date(nowMillis + ttl));
        }
        return builder.compact();
    }

    /**
     * 解析token
     * @param token
     * @return
     */
    public Claims parseJwt(String token){
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }

}
