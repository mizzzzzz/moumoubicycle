package com.togogotrain.moumoubicycle;

import com.togogotrain.moumoubicycle.entity.User;
import com.togogotrain.moumoubicycle.entity.UserExample;
import com.togogotrain.moumoubicycle.mappers.UserMapper;
import com.togogotrain.moumoubicycle.utils.WxApi;
import com.togogotrain.moumoubicycle.web.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.*;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MoumoubicycleApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Test
    public void contextLoads() {

        List<User> list = userMapper.selectByExample(new UserExample());
        for (User user :
                list) {
            System.out.println(user.toString());
        }

    }
/*    @Test
    public void getPolicyJson(){
        String appid = "wx2ef4abc66e701c0c";
        String secret = "fbbd3335e480004c8eaa65d3ca444d54";
        String js_code = "0236rX450MDbJK15VL650h4C4506rX4A";
        String grant_type = "authorization_code";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("appid", appid)
                .queryParam("secret", secret)
                .queryParam("js_code", js_code)
                .queryParam("grant_type", grant_type);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        HttpEntity<String> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                entity,
                String.class);
        System.out.println(response.getBody());
    }*/
    @Test
    public void wxapitest(){
        String js_code = "033ythF30hZjuC1bRBD30SXAF30ythFl";
        System.out.println(new WxApi().getWxOpenId(js_code));
    }

    @Test
    public void userServiceTest() {
        System.out.println(userService.hasUser("123"));
    }

    @Test
    public void userService() {
        System.out.println(userService.getUser("1213"));
    }
    @Test
    public void logintest(){
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andWxopenidEqualTo("");
        List<User> list = userMapper.selectByExample(userExample);
        System.out.println(list.toString());
    }
    @Test
    public void baseTest(){
        String str = "\"abc\"";
        System.out.println(str.replace("\"",""));
    }
}
