package com.togogotrain.moumoubicycle.web.controller;

import com.github.pagehelper.PageInfo;
import com.togogotrain.moumoubicycle.entity.Journey;
import com.togogotrain.moumoubicycle.entity.Locations;
import com.togogotrain.moumoubicycle.entity.User;
import com.togogotrain.moumoubicycle.entity.format.JourneyDetails;
import com.togogotrain.moumoubicycle.entity.format.Result;
import com.togogotrain.moumoubicycle.web.service.JourneyService;
import com.togogotrain.moumoubicycle.web.service.LocationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cjd
 * Date: 2018/10/12
 * Time: 0:17
 */
@RestController
public class JourneyController {
    //提供关于行程的接口方法

    @Autowired
    private JourneyService journeyService;
    @Autowired
    private LocationsService locationsService;

    //行程页面-行程列表
    @RequestMapping(value = "/getJourneys",method = RequestMethod.GET)
    public Result<PageInfo<Journey>> getJourneys(
            Long user_id//接收用户id
            ,Integer pageNum//接收页码数
            ,Integer pageSize) {//接收页面大小
        //判断接收参数
        if (user_id == null) {
            return new Result<>(
                    "1002"
                    , "用户未登录"
                    , null);
        }
        //?--------if (pageNum == null || pageSize == null)
        if (user_id == null || pageNum == null || pageSize == null) {
            return new Result<>(
                    "1001"
                    , "参数错误"
                    , null);
        }
        User user = new User();
        user.setId(user_id);
        return new Result<PageInfo<Journey>>(
                "0000"
                , "success"
                , new PageInfo<>(journeyService
                    .getJourneys(user, pageNum, pageSize)));//调用journeyService接口方法
    }

    //!mx行程详细
    @RequestMapping(value = "/getJourneyById",method = RequestMethod.GET)
    public Result<JourneyDetails> getJourneyById(
            Long journey_id,//接收行程id
            HttpSession session
    ){
        //判断接收参数
        User user = (User) session.getAttribute("USER_SESSION");
        if (user== null) {
            return new Result<>(
                    "1002"
                    , "用户未登录"
                    , null);
        }

        if (journey_id == null) {
            return new Result<>(
                    "1001"
                    , "参数错误"
                    , null);
        }
        Journey journey = journeyService.getJourneyById(journey_id);
        List locations = locationsService.getLocationsByJourney(journey);
        JourneyDetails journeyDetails = new JourneyDetails(journey,locations);

        return new Result<JourneyDetails>(
                "0000"
                , "success"
                , journeyDetails);
    }
}
