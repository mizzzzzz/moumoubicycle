package com.togogotrain.moumoubicycle.web.service;

import com.togogotrain.moumoubicycle.entity.Journey;
import com.togogotrain.moumoubicycle.entity.Locations;

import java.util.List;

public interface LocationsService {
    public List<Locations> getLocationsByJourney(Journey journey);//同一journey.id

    public int updateLocations(Locations locations);//改

    public int addLocations(Locations locations);//增

    public int delLocations(long id);//删
}
