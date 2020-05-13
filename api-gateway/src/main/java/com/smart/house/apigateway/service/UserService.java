package com.smart.house.apigateway.service;

import com.google.common.collect.Lists;
import com.smart.house.apigateway.dao.UserDao;
import com.smart.house.apigateway.model.User;
import com.smart.house.apigateway.service.otherService.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Value("${domain.name}")
    private String domainName;
    @Autowired
    private UserDao userDao;
    @Autowired
    private FileService fileService;
    /* ------------------------------用户注册------------------------------*/
    //添加用户
    public boolean addUser(User user) {
        List<String> imgList = fileService.getImgPaths(Lists.newArrayList(user.getAvatarFile()));  //获取相对路径列表
        if(!imgList.isEmpty()){
            user.setAvatar(imgList.get(0));    //设置文件相对路径
            user.setAvatarFile(null);
        }
        //设置激活链接前缀
        user.setEnableUrl("http://"+domainName+"/user/activate");
        Boolean b=userDao.addUser(user);
        return b;
    }
    //激活用户
    public Boolean enable(String key) {
        Boolean b=userDao.enable(key);
        return b;
    }
    /* ------------------------------用户登入------------------------------*/
    public User selectUsers(String username, String password) {
        User user=new User();
        user.setEmail(username);
        user.setPasswd(password);
        User user1=userDao.selectUsers(user);
        return user1;
    }
    /* ------------------------------用户登出------------------------------*/

    public void logout(String token) {
        userDao.logout(token);
    }
    /* ------------------------------个人信息页------------------------------*/

    public User updateUser(User user) {
        return userDao.updateUser(user);
    }
}
