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
       1��ͨ��redis��ȡ�û�
       2�����û�У���ͨ�����ݿ��ȡ
       3����ȡ�������redis�У���Чʱ��Ϊ5min��
     */
    public User selectUserById(Integer id) {
        User user1=null;
        User user=new User();
        user.setId(id);
        //�ȴ�redis�л�ȡ
        String userString=stringRedisTemplate.opsForValue().get("user"+id);
        if(Strings.isEmpty(userString)){
                //redis��û�У������ݿ��л�ȡ
                user1= userDao.selectUsers(user).get(0);
                user1.setAvatar(fileprefix+user1.getAvatar());  //ƴ���û�ͷ��
            //��user�������л���json�ַ���
           String user1String=JSON.toJSONString(user1);
           //���ַ�������redis��
           stringRedisTemplate.opsForValue().set("user"+id,user1String);
           //���û������ʱ��
           stringRedisTemplate.expire("user"+id,5, TimeUnit.MINUTES);
        }else {
            //���redis���У�ֱ�ӽ�json�ַ��������л���user����
               user1= JSON.parseObject(userString,User.class);
        }
        return user1;
    }

    /* --------------------------------�����û���ƴ��ͷ��-----------------------------------*/
    public List<User> selectUsersByUser(User user) {
        List<User> userList=userDao.selectUsers(user);
        //���ͷ��ǰ׺
        userList.forEach(user1 -> user1.setAvatar(fileprefix+user1.getAvatar()));
        return userList;
    }
    /* ---------------------------------�û�ע��ģ��--------------------------------------*/

    /**
     * ����û�
     */
    public void addUser(User user, String enableUrl) {
        user.setPasswd(Salt.encryPassword(user.getPasswd()));    //�������
        user.setCreateTime(new Date(System.currentTimeMillis()));    //ע��ʱ��
        user.setEnable(0);                                       //δ����
        userDao.addUser(user);               //����û�
        //���ͼ�������
        mailService.registerNotify(user.getEmail(),enableUrl);
    }
    /**
     *  �����û�
     */
    public boolean enable(String key) {
        //ͨ��key��ȡredis�е�email
        String email=stringRedisTemplate.opsForValue().get(key);
        if(StringUtils.isBlank(email)) {
            System.out.println("��Ч��kry");
        }
        User user=new User();
        user.setEmail(email);
        user.setEnable(1);    //�����û�
        userDao.updateUser(user); //�����û�
        return true;
    }
    /* -------------------------------------�û�����ģ��----------------------------------------*/

    /**
     *��¼��֤
     */
    public User auth(String email, String passwd) {
        if(StringUtils.isBlank(email)||StringUtils.isBlank(passwd)){
            return null;
        }
        User user=new User();
        user.setEmail(email);
        user.setPasswd(Salt.encryPassword(passwd));
        user.setEnable(1);  //1���������û�0�������û�
        //��ѯ�û�
        List<User> userList=selectUsersByUser(user);
        if(!userList.isEmpty()){
            User user1=userList.get(0);
            //���û�������Ϣ�洢��jwt��
           String jwt= JwtHelper.genToken(ImmutableMap.of("email",user1.getEmail(),"userName",user1.getUserName(),"ts", Instant.now().getEpochSecond()+""));

           renewToken(jwt,email);
           user1.setToken(jwt);
           return user1;
        }
        return null;
    }
    //����jwt����ʱ��(30min)
    private String renewToken(String jwt,String email){
          stringRedisTemplate.opsForValue().set(email,jwt);
          stringRedisTemplate.expire(email,30,TimeUnit.MINUTES);
          return jwt;
    }

    /**
     *�����֤,ÿ�����󶼻�ִ��
     */
    public User getUserByToken(String jwt) {
        Map<String,String> map=null;
        try{
            //��ȡjwt���û�����Ϣ
            map= JwtHelper.verifyToken(jwt);
        }catch(Exception exception){
            return null;
    }
         String email= map.get("email");
        //��ȡtoken����Чʱ��
        Long expired=stringRedisTemplate.getExpire(email);
        if(expired>0){
            //��û����������jwt����ʱ��
            renewToken(jwt,email);
            User user=new User();
            user.setEmail(email);
            //��ѯ�û�������
            User user1=selectUsersByUser(user).get(0);
            if(user1==null){
                return null;
            }
            //��jwt����user
            user1.setToken(jwt);
            return user1;
        }

        System.out.println("token�ѹ���");
        return null;

    }
    /* ------------------------------------�û��ǳ�-----------------------------------------*/

    public void invalidate(String jwt) {
        Map<String,String> map= JwtHelper.verifyToken(jwt);
        stringRedisTemplate.delete(map.get("email"));
    }
    /* ------------------------------------������Ϣģ��--------------------------------------*/

    /**
     *�޸ĸ�����Ϣ(����,����,���ҽ���)
     */
    public User updateUser(User user) {
        userDao.updateUser(user);
        User user1=new User();
        user1.setEmail(user.getEmail());
        //��ȡ�޸ĺ��û�
        return  selectUsersByUser(user1).get(0);
    }
}
