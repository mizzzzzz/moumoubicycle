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

    //获取用户openId
    @Override
    public String getUserOpenId(String code) {
        WxApi wxApi = new WxApi();
        return wxApi.getWxOpenId(code);
    }

    //是否存在对应openid的用户
    @Override
    public boolean hasUser(String userOppenId) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andWxopenidEqualTo(userOppenId);
        return !userMapper.selectByExample(userExample).isEmpty();
    }

    //根据openId获取用户
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

    @Override
    public int updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int addUser(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public int delUser(long id) {
        return userMapper.deleteByPrimaryKey(id);
    }


}
