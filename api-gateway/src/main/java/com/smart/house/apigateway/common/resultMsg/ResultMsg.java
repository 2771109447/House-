package com.smart.house.apigateway.common.resultMsg;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ResultMsg {
    public  static final String errorMsgKey="errorMsg";
    public  static final String successMsgKey="successMsg";
    private String errorMsg;    //错误信息
    private String successMsg;  //成功信息

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getSuccessMsg() {
        return successMsg;
    }

    public void setSuccessMsg(String successMsg) {
        this.successMsg = successMsg;
    }
    //创建一个错误对象，并初始化
    public static ResultMsg errorMsg(String msg){
        ResultMsg  resultMsg=new ResultMsg();
        resultMsg.setErrorMsg(msg);
        return resultMsg;
    }
    //创建一个成功对象，并初始化
    public static ResultMsg successMsg(String msg){
        ResultMsg  resultMsg=new ResultMsg();
        resultMsg.setSuccessMsg(msg);
        return resultMsg;
    }
    //验证成功
    public Boolean isSuccess(){
        return errorMsg=="";
    }
    //创建一个map，保存返回信息
    public Map<String,String>  asMap(){
        Map<String,String>  map= Maps.newHashMap();
        map.put(successMsgKey,successMsg);
        map.put(errorMsgKey,errorMsg);
        return map;
    }


}
