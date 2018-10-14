package com.togogotrain.moumoubicycle.web.service;

import com.togogotrain.moumoubicycle.entity.Journey;
import com.togogotrain.moumoubicycle.entity.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cjd
 * Date: 2018/10/9
 * Time: 10:51
 */
public interface UserService {
    public String getUserOpenId(String code);

    public boolean hasUser(String userOppenId);//是否有这个用户

    public User getUser(String userOppenId);//获得该用户

    public int updateUser(User user);//改

    public int addUser(User user);//增
}
