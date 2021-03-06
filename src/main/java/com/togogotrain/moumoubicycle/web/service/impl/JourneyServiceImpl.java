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

    //获取用户的行程记录，并分页
    @Override
    public List<Journey> getJourneys(User user, int pageNum, int pageSize) {
        Long user_id = user.getId();
        JourneyExample journeyExample = new JourneyExample();
        JourneyExample.Criteria criteria = journeyExample.createCriteria();
        criteria.andUserEqualTo(user_id);
        PageHelper.startPage(pageNum, pageSize);
        return  journeyMapper.selectByExample(journeyExample);
    }

    //mx获取行程（通过Journey_id）
    @Override
    public Journey getJourneyById(long Journey_id){
        JourneyExample journeyExample = new JourneyExample();
        JourneyExample.Criteria criteria = journeyExample.createCriteria();
        criteria.andIdEqualTo(Journey_id);
        if (journeyMapper.selectByExample(journeyExample).isEmpty()) {
            return null;
        } else {
            return journeyMapper.selectByExample(journeyExample).get(0);
        }
    }

    @Override
    public int updateJourney(Journey journey) {
        return journeyMapper.updateByPrimaryKeySelective(journey);
    }

    @Override
    public int addJourney(Journey journey) {
        return journeyMapper.insertSelective(journey);
    }

    @Override
    public int delJourney(long id) {
        return journeyMapper.deleteByPrimaryKey(id);
    }
}
