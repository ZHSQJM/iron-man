package com.zhs.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/9 15:43
 * @package: com.zhs.common
 * @description:
 */
@Data
public class JwtTokenUtil {

    private String secret;

    private Long expiration;

    private String header;

    public  JwtTokenUtil(String secret,Long expiration,String header){
        this.secret = secret;
        this.expiration = expiration;
        this.header = header;
    }

    /**
     * 生成令牌
     * @param username
     * @return
     */
    public String generateToken(String username){
        Map<String,Object> claims = new HashMap<>(2);
        claims.put("sub",username);
        claims.put("creatd",new Date());
        return generateToken(claims);
    }

    private String generateToken(Map<String,Object> claims){
        Date expirationDate = new Date(System.currentTimeMillis() + expiration);
        return Jwts.builder().setClaims(claims)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    /**
     * 获取用户名称
     * @param token
     * @return
     */
    public String getUsernameFromToken(String token){
        String username;
        try{
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        }catch (Exception e){
            username = null;
        }
        return username;
    }

    private Claims getClaimsFromToken(String token){
        Claims claims;
        try {
             claims = Jwts.parser().setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            claims = null;
        }
        return claims;
    }

    /**
     * 判断令牌是否过期
     * @param token
     * @return
     */
    public boolean isTokenExpired(String token){
        try {
            final Claims claimsFromToken = getClaimsFromToken(token);
            final Date expiration = claimsFromToken.getExpiration();
            return expiration.before(new Date());
        }catch (Exception e){
            return false;
        }
    }

    public String refreshToken(String token){
        String refreshToken;
        try{
            final Claims claimsFromToken = getClaimsFromToken(token);
            claimsFromToken.put("created",new Date());
            refreshToken = generateToken(claimsFromToken);
        }catch (Exception e){
            refreshToken = null;
        }
        return refreshToken;
    }

    /**
     * 验证令牌
     * @param token
     * @param username
     * @return
     */
    public Boolean validateToken(String token,String username){
        final String usernameFromToken = getUsernameFromToken(token);
        return (username.equalsIgnoreCase(usernameFromToken) && !isTokenExpired(token));
    }
}
