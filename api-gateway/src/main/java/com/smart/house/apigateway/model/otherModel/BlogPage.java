package com.smart.house.apigateway.model.otherModel;

import com.smart.house.apigateway.common.page.PageParams;
import com.smart.house.apigateway.model.Blog;

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
