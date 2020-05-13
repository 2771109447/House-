package com.smart.house.apigateway.controller;

import com.smart.house.apigateway.model.House;
import com.smart.house.apigateway.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
public class indexController {

    @Autowired
    private RecommendService recommendService;
    @RequestMapping("/")
    public String morenIndex(){
        return "redirect:index";
    }

    @RequestMapping("index")
    public String index(Model model){
        //最新上市
        List<House> houses=recommendService.selectRecommendHouses();
        model.addAttribute("recomHouses",houses);

        return "homepage/index";
    }
}
