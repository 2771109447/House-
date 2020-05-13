package com.smart.house.servicehouse.common.page;

import java.util.List;

public class PageData<T> {

    private List<T> list;    //返回的结果列表
    private Pagination pagination;// 分页对象

    //构造函数
    public PageData(Pagination pagination,List<T> list){
        this.pagination = pagination;
        this.list = list;
    }

    public PageData() {
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
    //初始化Pagination与结果列表
    public static  <T> PageData<T> buildPage(List<T> list, Integer count, Integer pageSize, Integer pageNum){
        Pagination _pagination = new Pagination(pageSize, pageNum,count);
        return new PageData<>(_pagination, list);
    }

}
