package com.togogotrain.moumoubicycle.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.togogotrain.moumoubicycle.entity.Journey;
import com.togogotrain.moumoubicycle.entity.JourneyExample;
import com.togogotrain.moumoubicycle.entity.User;
import com.togogotrain.moumoubicycle.entity.UserExample;
import com.togogotrain.moumoubicycle.mappers.UserMapper;
import com.togogotrain.moumoubicycle.utils.WxApi;
import com.togogotrain.moumoubicycle.web.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cjd
 * Date: 2018/10/9
 * Time: 11:44
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public String getUserOpenId(String code) {
        WxApi wxApi = new WxApi();
        return wxApi.getWxOpenId(code);
    }

    @Override
    public boolean hasUser(String userOppenId) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andWxopenidEqualTo(userOppenId);
        return !userMapper.selectByExample(userExample).isEmpty();
    }

    @Override
    public User getUser(String userOppenId) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andWxopenidEqualTo(userOppenId);
        List<User> userList = userMapper.selectByExample(userExample);
        if (userList.isEmpty()) {
            return null;
        } else {
            return userList.get(0);
        }


    }
}
