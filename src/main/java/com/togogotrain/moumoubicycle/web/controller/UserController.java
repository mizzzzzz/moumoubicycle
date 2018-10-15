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
            httpSession.setAttribute("USER_SESSION", user);
            user.setWxopenid(null);//不传回前端，不想被别人拿到
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
    //！mx充值页面跳转
    @RequestMapping(value = "/toRechargePage", method = RequestMethod.GET)
    public String toRechargePage(){
        return "toRechargePage";
    }
    //！mx充值操作
    @RequestMapping(value = "/recharge", method = RequestMethod.GET)
    public Result recharge(
            Float add_money,
            HttpSession httpSession)
    {//判断接收参数
        User user = (User) httpSession.getAttribute("USER_SESSION");
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

        User newUser = userService.getUser(user.getWxopenid());
        httpSession.setAttribute("USER_SESSION", newUser);//更新httpSession
        Float currentMoney = newUser.getMoney();

        return new Result<>(
                "0000"
                , "success"
                , currentMoney);
    }
    //！mx充值页面跳转
    @RequestMapping(value = "/toDepositPage", method = RequestMethod.GET)
    public String toDepositPage(){
        return "toDepositPage";
    }
    //！mx缴纳押金操作
    @RequestMapping(value = "/deposit", method = RequestMethod.GET)
    public Result deposit(HttpSession httpSession){
        //判断接收参数
        User user = (User) httpSession.getAttribute("USER_SESSION");
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

        User newUser = userService.getUser(user.getWxopenid());
        httpSession.setAttribute("USER_SESSION", newUser);//更新httpSession

        return new Result<>(
                "0000"
                , "success"
                , true);
    }
    //！mx查询用户信息（详情）
    @RequestMapping(value = "/userDetails", method = RequestMethod.GET)
    public Result<Map> userDetails(HttpSession httpSession){
        //判断接收参数
        User user = (User) httpSession.getAttribute("USER_SESSION");
        if (user== null) {
            return new Result<>(
                    "1002"
                    , "用户未登录"
                    , null);
        }
        User oldUser =userService.getUser(user.getWxopenid());

        if(oldUser==null){
            return new Result<>(
                    "2001"
                    , "user not register"
                    , null);
        }

        Map<String, Object> userDetails = new HashMap<String, Object>();
        userDetails.put("money",oldUser.getMoney());
        userDetails.put("user.getHasdeposit()",oldUser.getHasdeposit());
        return new Result<>(
                "0000"
                , "success"
                , userDetails);
    }
    //！mx注册页面跳转
    @RequestMapping(value = "/toUserRegisterPage", method = RequestMethod.GET)
    public String toUserRegisterPage(){
        return "toUserRegisterPage";
    }
    //！mx注册操作
    @RequestMapping(value = "/userRegister", method = RequestMethod.GET)
    public Result userRegister(
            String phone,
            HttpSession session
    ){
        //参数错误
        if (phone==null) {
            return new Result<>(
                    "1001"
                    , "参数错误"
                    , null);
        }

        User user = (User) session.getAttribute("USER_SESSION");

        User oldUser =userService.getUser(user.getWxopenid());
        //是否注册
        if(oldUser!=null){
            return new Result(
                    "2002"
                    , "user existence"
                    , null);
        }else {
            User re_user = new User();
            re_user.setWxopenid(user.getWxopenid());
            re_user.setPhone(phone);
            userService.addUser(re_user);

            User newUser = userService.getUser(user.getWxopenid());
            session.setAttribute("USER_SESSION", re_user);//更新httpSession
            return new Result(
                    "0000"
                    , "success"
                    , newUser);
        }
    }
}
