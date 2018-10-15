package com.togogotrain.moumoubicycle.web.service.impl;


import com.togogotrain.moumoubicycle.entity.Journey;
import com.togogotrain.moumoubicycle.entity.Locations;
import com.togogotrain.moumoubicycle.entity.LocationsExample;
import com.togogotrain.moumoubicycle.mappers.LocationsMapper;
import com.togogotrain.moumoubicycle.web.service.LocationsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LocationsServicelmpl implements LocationsService {

    @Resource
    private LocationsMapper locationsMapper;

    //获取坐标集（通过journey → journey.id=location.Journey）
    @Override
    public List<Locations> getLocationsByJourney(Journey journey) {
        Long locations_journey = journey.getId();
        LocationsExample locationsExample = new LocationsExample();
        LocationsExample.Criteria criteria = locationsExample.createCriteria();
        criteria.andJourneyEqualTo(locations_journey);
        return locationsMapper.selectByExample(locationsExample);
    }

    @Override
    public int updateLocations(Locations locations) {
        return locationsMapper.updateByPrimaryKeySelective(locations);
    }

    @Override
    public int addLocations(Locations locations) {
        return locationsMapper.insertSelective(locations);
    }

    @Override
    public int delLocations(long id) {
        return locationsMapper.deleteByPrimaryKey(id);
    }
}
