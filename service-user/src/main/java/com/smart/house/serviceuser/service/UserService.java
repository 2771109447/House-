package com.smart.house.serviceuser.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import com.smart.house.serviceuser.common.JWT.JwtHelper;
import com.smart.house.serviceuser.common.Salt;
import com.smart.house.serviceuser.dao.UserDao;
import com.smart.house.serviceuser.model.User;
import com.smart.house.serviceuser.service.otherService.MailService;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {
    @Value("${file.prefix}")
    private String fileprefix;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    private UserDao userDao;
    @Autowired
    private MailService mailService;


    /**
     *
       1，通过redis获取用户
       2，如果没有，则通过数据库获取
       3，获取后将其存入redis中（有效时间为5min）
     */
    public User selectUserById(Integer id) {
        User user1=null;
        User user=new User();
        user.setId(id);
        //先从redis中获取
        String userString=stringRedisTemplate.opsForValue().get("user"+id);
        if(Strings.isEmpty(userString)){
                //redis中没有，则到数据库中获取
                user1= userDao.selectUsers(user).get(0);
                user1.setAvatar(fileprefix+user1.getAvatar());  //拼接用户头像
            //将user对象序列化成json字符串
           String user1String=JSON.toJSONString(user1);
           //将字符串存入redis中
           stringRedisTemplate.opsForValue().set("user"+id,user1String);
           //设置缓存过期时间
           stringRedisTemplate.expire("user"+id,5, TimeUnit.MINUTES);
        }else {
            //如果redis中有，直接将json字符串反序列化成user对象
               user1= JSON.parseObject(userString,User.class);
        }
        return user1;
    }

    /* --------------------------------查找用户（拼接头像）-----------------------------------*/
    public List<User> selectUsersByUser(User user) {
        List<User> userList=userDao.selectUsers(user);
        //添加头像前缀
        userList.forEach(user1 -> user1.setAvatar(fileprefix+user1.getAvatar()));
        return userList;
    }
    /* ---------------------------------用户注册模块--------------------------------------*/

    /**
     * 添加用户
     */
    public void addUser(User user, String enableUrl) {
        user.setPasswd(Salt.encryPassword(user.getPasswd()));    //密码加密
        user.setCreateTime(new Date(System.currentTimeMillis()));    //注册时间
        user.setEnable(0);                                       //未激活
        userDao.addUser(user);               //添加用户
        //发送激活链接
        mailService.registerNotify(user.getEmail(),enableUrl);
    }
    /**
     *  激活用户
     */
    public boolean enable(String key) {
        //通过key获取redis中的email
        String email=stringRedisTemplate.opsForValue().get(key);
        if(StringUtils.isBlank(email)) {
            System.out.println("无效的kry");
        }
        User user=new User();
        user.setEmail(email);
        user.setEnable(1);    //激活用户
        userDao.updateUser(user); //更新用户
        return true;
    }
    /* -------------------------------------用户登入模块----------------------------------------*/

    /**
     *登录验证
     */
    public User auth(String email, String passwd) {
        if(StringUtils.isBlank(email)||StringUtils.isBlank(passwd)){
            return null;
        }
        User user=new User();
        user.setEmail(email);
        user.setPasswd(Salt.encryPassword(passwd));
        user.setEnable(1);  //1代表启用用户0代表冻结用户
        //查询用户
        List<User> userList=selectUsersByUser(user);
        if(!userList.isEmpty()){
            User user1=userList.get(0);
            //将用户部分信息存储到jwt中
           String jwt= JwtHelper.genToken(ImmutableMap.of("email",user1.getEmail(),"userName",user1.getUserName(),"ts", Instant.now().getEpochSecond()+""));

           renewToken(jwt,email);
           user1.setToken(jwt);
           return user1;
        }
        return null;
    }
    //设置jwt过期时间(30min)
    private String renewToken(String jwt,String email){
          stringRedisTemplate.opsForValue().set(email,jwt);
          stringRedisTemplate.expire(email,30,TimeUnit.MINUTES);
          return jwt;
    }

    /**
     *身份验证,每次请求都会执行
     */
    public User getUserByToken(String jwt) {
        Map<String,String> map=null;
        try{
            //获取jwt中用户的信息
            map= JwtHelper.verifyToken(jwt);
        }catch(Exception exception){
            return null;
    }
         String email= map.get("email");
        //获取token的有效时间
        Long expired=stringRedisTemplate.getExpire(email);
        if(expired>0){
            //还没过期则重置jwt过期时间
            renewToken(jwt,email);
            User user=new User();
            user.setEmail(email);
            //查询用户并返回
            User user1=selectUsersByUser(user).get(0);
            if(user1==null){
                return null;
            }
            //将jwt存入user
            user1.setToken(jwt);
            return user1;
        }

        System.out.println("token已过期");
        return null;

    }
    /* ------------------------------------用户登出-----------------------------------------*/

    public void invalidate(String jwt) {
        Map<String,String> map= JwtHelper.verifyToken(jwt);
        stringRedisTemplate.delete(map.get("email"));
    }
    /* ------------------------------------个人信息模块--------------------------------------*/

    /**
     *修改个人信息(密码,姓名,自我介绍)
     */
    public User updateUser(User user) {
        userDao.updateUser(user);
        User user1=new User();
        user1.setEmail(user.getEmail());
        //获取修改后用户
        return  selectUsersByUser(user1).get(0);
    }
}
