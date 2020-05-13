package com.smart.house.servicecomment.controller;

import com.smart.house.servicecomment.common.page.PageData;
import com.smart.house.servicecomment.model.Blog;
import com.smart.house.servicecomment.model.otherService.BlogPage;
import com.smart.house.servicecomment.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("blog")
public class BlogController {
    @Autowired
    private BlogService blogService;

    /*-------------------------------------博客列表---------------------------------*/
    /**
     *博客列表
     */
    @RequestMapping("blogList")
    public PageData<Blog> blogList(@RequestBody BlogPage blogPage) {

        PageData<Blog> pageData=blogService.selectBlogs(blogPage.getBlog(),blogPage.getPageParams());
        return pageData;
    }
    /*-------------------------------------博客详情---------------------------------*/
    @RequestMapping("blogDetial")
    public Blog blogDetial(Integer id) {
        Blog blog=blogService.selectOneBlog(id);
        return blog;
    }
}
