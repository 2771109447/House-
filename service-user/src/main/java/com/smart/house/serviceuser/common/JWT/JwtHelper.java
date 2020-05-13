package com.smart.house.serviceuser.common.JWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.collect.Maps;

public class JwtHelper {
  
  private static final String  SECRET = "session_secret";
  
  private static final String  ISSUER = "mooc_user";

  /**
   *生成jwt
   */
  public static String genToken(Map<String, String> claims){
    try {
      //定义生成jwt的算法,计算生成签名hash
      Algorithm algorithm = Algorithm.HMAC256(SECRET);
      //令牌发布者及过期时间（1day）
      JWTCreator.Builder builder = JWT.create().withIssuer(ISSUER).withExpiresAt(DateUtils.addDays(new Date(), 1));
      //将传来的数据存储到jwt中
      claims.forEach((k,v) -> builder.withClaim(k, v));
      //通过计算签名哈希生成并返回一个jwt
      return builder.sign(algorithm).toString();
    } catch (IllegalArgumentException | UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   *身份验证
   */
  public static Map<String, String> verifyToken(String token)  {
    //定义生成jwt的算法,计算生成签名hash
    Algorithm algorithm = null;
    try {
      algorithm = Algorithm.HMAC256(SECRET);
    } catch (IllegalArgumentException | UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
    //根据发布者与签名算法获取verifier
    JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
    //通过传来的token匹配jwt
    DecodedJWT jwt =  verifier.verify(token);
    //获取jwt里面的用户数据
    Map<String, Claim> map = jwt.getClaims();
    //将数据装换成string类型
    Map<String, String> resultMap = Maps.newHashMap();
    map.forEach((k,v) -> resultMap.put(k, v.asString()));
    //返回map
    return resultMap;
  }

}
