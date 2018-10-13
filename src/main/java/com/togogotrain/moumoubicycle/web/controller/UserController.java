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
import java.util.HashMap;
import java.util.Map;

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
    //mx充值
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
        if (user.getHasdeposit()== false) {
            return new Result<>(
                    "1003"
                    , "用户未缴纳押金"
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

        User oldUser =userService.getUser(user.getWxopenid());

        re_user.setMoney(oldUser.getMoney()+add_money);
        userService.updateUser(re_user);
        Float currentMoney = re_user.getMoney();
        return new Result<>(
                "0000"
                , "success"
                , currentMoney);
    }
    //mx交押金
    @RequestMapping(value = "/deposit", method = RequestMethod.GET)
    public Result deposit(HttpSession session){
        //判断接收参数
        User user = (User) session.getAttribute("USER_SESSION");
        if (user== null) {
            return new Result<>(
                    "1002"
                    , "用户未登录"
                    , null);
        }
        User re_user = new User();
        re_user.setId(user.getId());

        re_user.setHasdeposit(true);
        userService.updateUser(re_user);
        return new Result<>(
                "0000"
                , "success"
                , true);
    }
    //mx查询用户详情
    @RequestMapping(value = "/userDetails", method = RequestMethod.GET)
    public Result<Map> userDetails(HttpSession session){
        //判断接收参数
        User user = (User) session.getAttribute("USER_SESSION");
        if (user== null) {
            return new Result<>(
                    "1002"
                    , "用户未登录"
                    , null);
        }
        User oldUser =userService.getUser(user.getWxopenid());

        Map<String, Object> userDetails = new HashMap<String, Object>();
        userDetails.put("money",oldUser.getMoney());
        userDetails.put("user.getHasdeposit()",oldUser.getHasdeposit());
        return new Result<>(
                "0000"
                , "success"
                , userDetails);
    }
}
