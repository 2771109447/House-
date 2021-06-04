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
   *����jwt
   */
  public static String genToken(Map<String, String> claims){
    try {
      //��������jwt���㷨,��������ǩ��hash
      Algorithm algorithm = Algorithm.HMAC256(SECRET);
      //���Ʒ����߼�����ʱ�䣨1day��
      JWTCreator.Builder builder = JWT.create().withIssuer(ISSUER).withExpiresAt(DateUtils.addDays(new Date(), 1));
      //�����������ݴ洢��jwt��
      claims.forEach((k,v) -> builder.withClaim(k, v));
      //ͨ������ǩ����ϣ���ɲ�����һ��jwt
      return builder.sign(algorithm).toString();
    } catch (IllegalArgumentException | UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   *������֤
   */
  public static Map<String, String> verifyToken(String token)  {
    //��������jwt���㷨,��������ǩ��hash
    Algorithm algorithm = null;
    try {
      algorithm = Algorithm.HMAC256(SECRET);
    } catch (IllegalArgumentException | UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
    //���ݷ�������ǩ���㷨��ȡverifier
    JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
    //ͨ��������tokenƥ��jwt
    DecodedJWT jwt =  verifier.verify(token);
    //��ȡjwt������û�����
    Map<String, Claim> map = jwt.getClaims();
    //������װ����string����
    Map<String, String> resultMap = Maps.newHashMap();
    map.forEach((k,v) -> resultMap.put(k, v.asString()));
    //����map
    return resultMap;
  }

}