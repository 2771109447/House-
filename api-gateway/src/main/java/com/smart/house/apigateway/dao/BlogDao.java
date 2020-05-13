package com.smart.house.apigateway.dao;

import com.smart.house.apigateway.common.page.PageData;
import com.smart.house.apigateway.common.page.PageParams;
import com.smart.house.apigateway.model.Blog;
import com.smart.house.apigateway.model.otherModel.BlogPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class BlogDao {
    @Autowired
    private RestTemplate restTemplate;

    /**
     *博客列表
     */
    public PageData<Blog> selectBlogs(Blog blog, PageParams pageParams) {
        BlogPage blogPage=new BlogPage(blog,pageParams);
        String url="http://comment/blog/blogList";
        PageData<Blog> pageData=restTemplate.postForObject(url,blogPage,PageData.class);
        return pageData;
    }

    /**
     *博客详情
     */
    public Blog selectOneBlog(int id) {
        String url="http://comment/blog/blogDetial?id="+id;
        Blog blog=restTemplate.getForObject(url,Blog.class,id);
        return blog;
    }
}
