package com.togogotrain.moumoubicycle.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.io.IOException;
/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cjd
 * Date: 2018/10/8
 * Time: 23:39
 */
public class WxApi {
    //小程序appid以及secret
    private final String appid = "wx2ef4abc66e701c0c";
    private final String secret = "fbbd3335e480004c8eaa65d3ca444d54";
    //获取openId时需要的参数
    private final String grant_type = "authorization_code";

    //用于发起http请求，传入url以及请求参数，获得响应数据(JsonNode)
    public JsonNode getWxApi(String url,MultiValueMap<String,String> param) {
        //new RestTemplate类 用与发起http请求
        RestTemplate restTemplate = new RestTemplate();
        //设置请求头部,(暂时不需设置)
        HttpHeaders headers = new HttpHeaders();
        //拼接请求地址与请求参数的类builder
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(url).queryParams(param);
        //创建Entity
        HttpEntity<String> entity = new HttpEntity<>(headers);
        //获取响应entity
        HttpEntity<String> response = restTemplate.exchange(
                builder.build().encode().toUri(),//创建请求url
                HttpMethod.GET,//请求方法
                entity,//请求entity
                String.class);//响应返回类型
        //创建ObjectMapper,用于获取返回的json数据
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES
                , true);
        objectMapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES
                , false);
        //获取响应信息（String）
        String responseStr = response.getBody();
        try {
            //转化为json数据
            JsonNode jsonNode = objectMapper
                    .readValue(responseStr, JsonNode.class);
            return jsonNode;
        } catch (IOException e) {
            System.out.println("wxapi 返回有误");
            return null;
        }
    }

    /*获取微信OpeinId方法，按照微信小程序开发文档，需要传入code参数
    * code参数来自微信小程序前端调用wx.login()接口返回
    * code在后端向接口发起请求可获得用户的openId
    * */
    public String getWxOpenId(String code){
        //接口地址
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        //请求参数
        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        param.add("appid", appid);
        param.add("secret", secret);
        param.add("grant_type", grant_type);
        param.add("js_code",code);
        //调用发起请求方法 ，获得json
        JsonNode jsonNode = getWxApi(url, param);
        if (jsonNode != null) {
            //获取json 中的openid信息
            //一定要加asText()反则返回不为String 会有""
            String openid = jsonNode.get("openid").asText();
            if (openid != null) {
                return openid;
            } else {
                //请求错误 具体参考文档(后补充)
                return null;
            }
        }else {
//            请求错误 未知情况(后补充)
            return null;
        }
    }

/* //post请求可用 get请求不可用 参数会消失
    public void getPolicyJson(){
        RestTemplate restTemplate=new RestTemplate();
        String url=
                "https://api.weixin.qq.com/sns/jscode2session?appid={appid}&secret={secret}&js_code={js_code}&grant_type={grant_type}";
        *//* 注意：必须 http、https……开头，不然报错，浏览器地址栏不加 http 之类不出错是因为浏览器自动帮你补全了 *//*
        HttpHeaders headers = new HttpHeaders();
        *//* 这个对象有add()方法，可往请求头存入信息 *//*
        *//* 解决中文乱码的关键 , 还有更深层次的问题 关系到 StringHttpMessageConverter，先占位，以后补全*//*
        LinkedMultiValueMap<String,String> body = new LinkedMultiValueMap();
        body.add("appid", appid);
        body.add("secret", secret);
        body.add("js_code", js_code);
        body.add("grant_code", grant_type);
        HttpEntity entity = new HttpEntity(body, headers);
        *//* body是Http消息体例如json串 *//*
        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        *//*上面这句返回的是往 url发送 post请求 请求携带信息为entity时返回的结果信息
           String.class 是可以修改的，其实本质上就是在指定反序列化对象类型，这取决于你要怎么解析请求返回的参数*//*
        System.out.println(result.getBody());
    }*/
}
