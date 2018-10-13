package com.togogotrain.moumoubicycle.web.service;

import com.github.pagehelper.Page;
import com.togogotrain.moumoubicycle.entity.Journey;
import com.togogotrain.moumoubicycle.entity.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cjd
 * Date: 2018/10/11
 * Time: 21:59
 */
public interface JourneyService {

    public List<Journey> getJourneys(User user, int pageNum, int pageSize);
    public Journey getJourneyById(long id);

}
