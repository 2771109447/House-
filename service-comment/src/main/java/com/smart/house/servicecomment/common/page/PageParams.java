package com.smart.house.servicecomment.common.page;

public class PageParams {
    private static final Integer PAGE_SIZE = 2;
    private  Integer pageSize;  //每页的大小
    private  Integer pageNum;   //分页显示的当前页

    private  Integer offset;    //查询起始点（当前页之前的房产数量）
    private  Integer limit;     //查询数量

    public static PageParams build(Integer pageSize,Integer pageNum){
        if (pageSize == null) {
            pageSize = PAGE_SIZE;  //默认每页大小为2
        }
        if (pageNum == null) {
            pageNum = 1;     //默认当前页为首页
        }
        //调用构造函数，初始化offset.limit
        return new PageParams(pageSize, pageNum);
    }
    //默认的构造函数（首页，每页数量为2）
    public PageParams(){
        this(PAGE_SIZE, 1);
    }


    //构造函数
    public PageParams(Integer pageSize, Integer pageNum) {
        this.pageSize = pageSize;
        this.pageNum = pageNum;
        this.offset=(pageNum-1)*pageSize;
        this.limit=pageSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
