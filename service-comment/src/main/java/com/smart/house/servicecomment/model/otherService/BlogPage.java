package com.smart.house.servicecomment.model.otherService;


import com.smart.house.servicecomment.common.page.PageParams;
import com.smart.house.servicecomment.model.Blog;

public class BlogPage {

    private Blog blog;
    private PageParams pageParams;

    public BlogPage() {
    }

    public BlogPage(Blog blog, PageParams pageParams) {
        this.blog = blog;
        this.pageParams = pageParams;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public PageParams getPageParams() {
        return pageParams;
    }

    public void setPageParams(PageParams pageParams) {
        this.pageParams = pageParams;
    }
}
