package com.example.CoronavirusTracker.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationFacts {
    private String state;
    private String country;
    private int LatestConfirmedCases;
    private int newCasesYesterday;

    @Override
    public String toString() {
        return "LocationFacts{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", LatestConfirmedCases=" + LatestConfirmedCases +
                ", newCasesYesterday=" + newCasesYesterday +
                '}';
    }
}
