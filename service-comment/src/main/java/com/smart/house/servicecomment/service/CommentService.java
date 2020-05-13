package com.smart.house.servicecomment.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.base.Strings;
import com.smart.house.servicecomment.dao.CommentDao;
import com.smart.house.servicecomment.dao.UserDao;
import com.smart.house.servicecomment.model.Comment;
import com.smart.house.servicecomment.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class CommentService {
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private UserDao userDao;
    /**
     *添加房产评论
     */
    public void addHouseComment(Integer houseId, String content, Integer userId) {
        Comment comment=new Comment();
        comment.setHouseId(houseId);//房产id
        comment.setBlogId(0);
        comment.setUserId(userId); //评论人
        comment.setContent(content);//评论内容
        comment.setType(1);    //评论为房产类型
        comment.setCreateTime(new Date(System.currentTimeMillis()));  //评论时间
        commentDao.addHouseComment(comment);
        //每添加一条评论就将对应房产所有评论从redis中移除，以便更新评论
        redisTemplate.delete(redisTemplate.keys("house_comments"+"_"+houseId));


    }
    /**
     *添加博客评论
     */
    public void addBlogComment(Integer blogid, String content, Integer userId) {
        Comment comment=new Comment();
        comment.setBlogId(blogid); //博客id
        comment.setHouseId(0);
        comment.setUserId(userId); //评论人
        comment.setContent(content);//评论内容
        comment.setType(2);    //评论为博客类型
        comment.setCreateTime(new Date(System.currentTimeMillis()));  //评论时间
        commentDao.addHouseComment(comment);
        //每添加一条评论就将对于博客所有评论从redis中移除，以便更新评论
        redisTemplate.delete(redisTemplate.keys("blog_comments"+"_"+blogid));

    }

    /**
     *获取房产评论列表
     */
    public List<Comment> selectAllHouseComment(Integer id) {
        //先从redis中获取
        String key  ="house_comments" + "_" + id;
        String json = redisTemplate.opsForValue().get(key);
        List<Comment> lists = null;
        //如果没有，则在数据库中获取，并将保存到redis中，过期时间为5min
        if (Strings.isNullOrEmpty(json)) {
            lists = doGetHouseComments(id,8);
            //序列化成json字符串，保存到redis中
            redisTemplate.opsForValue().set(key, JSON.toJSONString(lists));
            redisTemplate.expire(key, 5, TimeUnit.MINUTES);
        }else {
            //如果redis中存在，直接反序列化成对象并返回
            lists =  JSON.parseObject(json,new TypeReference<List<Comment>>(){});
        }
        return lists;
    }

    /**
     *数据库中获取房产评论，并调用用户服务查询评论人
     */
    public List<Comment> doGetHouseComments(Integer houseId,Integer size) {
        List<Comment>  comments = commentDao.selectHouseComments(houseId, size);
        comments.forEach(comment -> {
            //调用用户服务查询评论人
            User user = userDao.getUserById(comment.getUserId());
            comment.setAvatar(user.getAvatar());  //设置评论人头像
            comment.setUserName(user.getUserName());//设置评论人姓名
        });
        return comments;
    }

    /**
     *获取博客评论列表
     */
    public List<Comment> selectAllBlogComment(Integer id) {
        //先从redis中获取
        String key  ="blog_comments" + "_" + id;
        String json = redisTemplate.opsForValue().get(key);
        List<Comment> lists = null;
        //如果没有，则在数据库中获取，并将保存到redis中，过期时间为5min
        if (Strings.isNullOrEmpty(json)) {
            lists = doGetblogComments(id,8);
            //序列化成json字符串，保存到redis中
            redisTemplate.opsForValue().set(key, JSON.toJSONString(lists));
            redisTemplate.expire(key, 5, TimeUnit.MINUTES);
        }else {
            //如果redis中存在，直接反序列化成对象并返回
            lists =  JSON.parseObject(json,new TypeReference<List<Comment>>(){});
        }
        return lists;
    }
    /**
     *数据库中获取博客评论，并调用用户服务查询评论人
     */
    public List<Comment> doGetblogComments(Integer houseId,Integer size) {
        List<Comment>  comments = commentDao.selectBlogComments(houseId, size);
        comments.forEach(comment -> {
            //调用用户服务查询评论人
            User user = userDao.getUserById(comment.getUserId());
            comment.setAvatar(user.getAvatar());  //设置评论人头像
            comment.setUserName(user.getUserName());//设置评论人姓名
        });
        return comments;
    }
}
