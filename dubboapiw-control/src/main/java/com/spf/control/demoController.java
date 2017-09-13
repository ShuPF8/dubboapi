package com.spf.control;

import com.alibaba.fastjson.JSON;
import com.spf.model.entity.SfUser;
import com.spf.remote.demo.DemoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Auther SPF
 * @Create 2017/8/9
 */
@Controller
public class demoController {

    @Autowired private DemoClient demoClient;

    @RequestMapping(value = "demo1", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String demo1(HttpServletRequest request){
        String str = demoClient.getName("Hello", "ShuPF");
        return str;
    }

    @RequestMapping(value = "demo2", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String dem2(){
        List<SfUser> list = demoClient.findAll();
        return JSON.toJSONString(list);
    }

}
