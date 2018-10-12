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

    public boolean hasUser(String userOppenId);

    public User getUser(String userOppenId);

}
