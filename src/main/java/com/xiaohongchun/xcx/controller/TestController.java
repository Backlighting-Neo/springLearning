package com.xiaohongchun.xcx.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiaohongchun.xcx.config.Config;
import com.xiaohongchun.xcx.service.QcloudSign;
import com.xiaohongchun.xcx.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Neo on 17/2/22.
 */
@RestController
@RequestMapping(value = "/api")
public class TestController {

    @Autowired
    private QcloudSign qCloudSign;

    @RequestMapping(value = "/getQcloudImageSign", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getQCloudImageSign(){
        qCloudSign.setAppID("1252313296");
        qCloudSign.setAppSecret("kIj3e3GIAuSo3A6qa8Ur5CDHNm6FE1c3");
        qCloudSign.setBucketName("xcximage");
        qCloudSign.setSecretID("AKIDxmU3UyFbgZGoAL4B8ye8Tq5MqFSvnQQE");

        String qCloudImageSign = qCloudSign.getSign();

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", qCloudImageSign);

        JSONObject body = new JSONObject();
        JSONArray urlList = new JSONArray();

        body.put("appid", "1252313296");
        body.put("bucket", "xcximage");
        body.put("card_type", 0);
        urlList.add(0, "http://img-qcloud.xiaohongchun.com.cn/prd-goods-images/zhouxiaohong_idcard_1.jpg");
        body.put("url_list", urlList);

//        HttpUtils.post("http://service.image.myqcloud.com/ocr/idcard", headers, body.toJSONString(), );




        return "";
    }


}
