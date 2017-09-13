package com.spf.remote.demo;

import com.spf.facede.api.DemoApi;
import com.spf.model.entity.SfUser;
import com.spf.remote.client.Client;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther SPF
 * @Create 2017/8/9
 */
@Component
public class DemoClient extends Client {

    public String getName(String str, String str2) {
        DemoApi demoApi = getDubboClient(DemoApi.class);
        return demoApi.getName(str,str2);
    }

    public List<SfUser> findAll(){
        DemoApi demoApi = getDubboClient(DemoApi.class);
        return demoApi.findAll();
    }

}
