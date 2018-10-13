package com.togogotrain.moumoubicycle.web.controller;

import com.togogotrain.moumoubicycle.entity.User;
import com.togogotrain.moumoubicycle.entity.UserExample;
import com.togogotrain.moumoubicycle.entity.format.Result;
import com.togogotrain.moumoubicycle.mappers.UserMapper;
import com.togogotrain.moumoubicycle.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cjd
 * Date: 2018/10/7
 * Time: 21:40
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/wxLogin", method = RequestMethod.GET)
    public Result<User> wxLogin(HttpSession httpSession , String code){
        //调用userService的getUserOpenId方法，获取用户openId
        String userOppenId = userService.getUserOpenId(code);
        //获取用户信息
        User user = userService.getUser(userOppenId);
        //判断用户是否注册
        if (user!=null) {
            user.setWxopenid(null);
            httpSession.setAttribute("USER_SESSION", user);
            return new Result<User>(
                            "0000"
                            , "success"
                            , user);
        } else {
            return new Result<User>(
                            "2001"
                            , "user not register"
                            , null);
        }
    }
    //充值
    @RequestMapping(value = "/recharge", method = RequestMethod.GET)
    public Result recharge(
            Float add_money,
            HttpSession session)
    {//判断接收参数
        User user = (User) session.getAttribute("USER_SESSION");
        if (user== null) {
            return new Result<>(
                    "1002"
                    , "用户未登录"
                    , null);
        }

        if (add_money==null) {
            return new Result<>(
                    "1001"
                    , "参数错误"
                    , null);
        }
        User re_user = new User();
        re_user.setId(user.getId());
        re_user.setMoney(user.getMoney()+add_money);
        System.out.println(re_user.getMoney());
        userService.updateUser(re_user);
        Float currentMoney = re_user.getMoney();
        return new Result<>(
                "0000"
                , "success"
                , currentMoney);
    }
}
