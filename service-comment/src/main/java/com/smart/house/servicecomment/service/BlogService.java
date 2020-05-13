package com.smart.house.servicecomment.service;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.smart.house.servicecomment.common.page.PageData;
import com.smart.house.servicecomment.common.page.PageParams;
import com.smart.house.servicecomment.dao.BlogDao;
import com.smart.house.servicecomment.model.Blog;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
    @Autowired
    private BlogDao blogDao;
    /*-------------------------------------博客列表---------------------------------*/
    /**
     *博客列表
     */
    public PageData<Blog> selectBlogs(Blog blog, PageParams pageParams) {
        List<Blog> blogs = blogDao.selectBlogList(blog, pageParams);
        //解析博客表的数据
        populate(blogs);
        //查询博客总数
        Integer count =  blogDao.selectBlogCount(blog);
        return PageData.buildPage(blogs,count,pageParams.getPageSize(),pageParams.getPageNum());
    }

    /**
     *解析blog
     */
    private void populate(List<Blog> blogs) {
        if (!blogs.isEmpty()) {
            blogs.stream().forEach(item -> {
                //解析html获取文本
                String stripped = Jsoup.parse(item.getContent()).text();
                //截取前40个字作为引文
                item.setDigest(stripped.substring(0, Math.min(stripped.length(),40)));
                String tags = item.getTags();
                //将标签分割存入数组中
                item.getTagList().addAll(Lists.newArrayList(Splitter.on(",").split(tags)));
            });
        }
    }

    /*-------------------------------------博客详情---------------------------------*/
    //博客详情
    public Blog selectOneBlog(Integer id) {
        Blog blog=new Blog();
        blog.setId(id);
        return blogDao.selectBlogList(blog,PageParams.build(1,1)).get(0);
    }
}
