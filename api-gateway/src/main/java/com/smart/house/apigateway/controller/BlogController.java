package com.smart.house.apigateway.controller;

import com.smart.house.apigateway.common.page.PageData;
import com.smart.house.apigateway.common.page.PageParams;
import com.smart.house.apigateway.model.Blog;
import com.smart.house.apigateway.model.Comment;
import com.smart.house.apigateway.model.House;
import com.smart.house.apigateway.service.BlogService;
import com.smart.house.apigateway.service.CommentService;
import com.smart.house.apigateway.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("blog")
public class BlogController {
    @Autowired
    private  RecommendService recommendService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private CommentService commentService;

    /**
     *�����б�
     */
    @RequestMapping(value="list")
    public String list(Integer pageSize, Integer pageNum, Blog blog, Model model){
        PageData<Blog> ps = blogService.selectBlogs(blog, PageParams.build(pageSize, pageNum));
        //���ŷ���
        List<House> houses =  recommendService.getHotHouse(3);

        model.addAttribute("recomHouses", houses);
        model.addAttribute("ps", ps);   //�����б�
        return "blog/listing";
    }
    /**
     * ��������
     */
    @RequestMapping(value="detail")
    public String blogDetail(Integer id,Model Model){
        //��������
        Blog blog = blogService.selectOneBlog(id);
        List<Comment> comments =  commentService.selectAllBlogComment(id);
        //���ŷ���
        List<House> houses =  recommendService.getHotHouse(3);
        Model.addAttribute("recomHouses", houses);
        Model.addAttribute("blog", blog);
        Model.addAttribute("commentList", comments);  //���������б�
        return "blog/detail";
    }
}
