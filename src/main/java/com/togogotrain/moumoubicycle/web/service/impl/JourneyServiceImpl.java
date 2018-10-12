package com.togogotrain.moumoubicycle.web.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.togogotrain.moumoubicycle.entity.Journey;
import com.togogotrain.moumoubicycle.entity.JourneyExample;
import com.togogotrain.moumoubicycle.entity.User;
import com.togogotrain.moumoubicycle.mappers.JourneyMapper;
import com.togogotrain.moumoubicycle.web.service.JourneyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: cjd
 * Date: 2018/10/11
 * Time: 22:00
 */
@Service("journryService")
public class JourneyServiceImpl implements JourneyService {

    @Resource
    private JourneyMapper journeyMapper;

    @Override
    public List<Journey> getJourneys(User user, int pageNum, int pageSize) {
        Long user_id = user.getId();
        JourneyExample journeyExample = new JourneyExample();
        JourneyExample.Criteria criteria = journeyExample.createCriteria();
        criteria.andUserEqualTo(user_id);
        PageHelper.startPage(pageNum, pageSize);
        return  journeyMapper.selectByExample(journeyExample);
    }
}
