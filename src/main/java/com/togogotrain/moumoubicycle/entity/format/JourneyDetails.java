package com.togogotrain.moumoubicycle.entity.format;

import com.togogotrain.moumoubicycle.entity.Journey;
import com.togogotrain.moumoubicycle.entity.Locations;

import java.util.List;

public class JourneyDetails {
    private Journey journey;
    private List<Locations> locationsList;

    public JourneyDetails(Journey journey, List<Locations> locationsList) {
        this.journey = journey;
        this.locationsList = locationsList;
    }

    public JourneyDetails() {
    }

    @Override
    public String toString() {
        return "JourneyDetails{" +
                "journey=" + journey +
                ", locationsList=" + locationsList +
                '}';
    }

    public Journey getJourney() {
        return journey;
    }

    public void setJourney(Journey journey) {
        this.journey = journey;
    }

    public List<Locations> getLocationsList() {
        return locationsList;
    }

    public void setLocationsList(List<Locations> locationsList) {
        this.locationsList = locationsList;
    }
}
