package com.togogotrain.moumoubicycle.web.service;

import com.togogotrain.moumoubicycle.entity.Journey;
import com.togogotrain.moumoubicycle.entity.Locations;

import java.util.List;

public interface LocationsService {
    public List<Locations> getLocationsByJourney(Journey journey);
}
