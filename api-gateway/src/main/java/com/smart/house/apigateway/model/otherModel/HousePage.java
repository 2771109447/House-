package com.smart.house.apigateway.model.otherModel;

import com.smart.house.apigateway.common.page.PageParams;
import com.smart.house.apigateway.model.House;

public class HousePage {
    private House house;
    private PageParams pageParams;

    public HousePage(House house, PageParams pageParams) {
        this.house = house;
        this.pageParams = pageParams;
    }

    public HousePage() {
    }

    public PageParams getPageParams() {
        return pageParams;
    }

    public void setPageParams(PageParams pageParams) {
        this.pageParams = pageParams;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }


}
