package com.example.UseCase_TravelPlanner.entity;

import lombok.Data;

@Data
public class AllTravelDetails {
    private Itinerary itinerary;
    private Activity activity;

    public AllTravelDetails(Itinerary itinerary, Activity activity) {
        this.itinerary = itinerary;
        this.activity = activity;
    }

    public AllTravelDetails() {}
}


