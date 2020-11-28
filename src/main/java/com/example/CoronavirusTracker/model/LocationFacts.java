package com.example.CoronavirusTracker.model;

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

    public int getNewCasesYesterday() {
        return newCasesYesterday;
    }

    public void setNewCasesYesterday(int newCasesYesterday) {
        this.newCasesYesterday = newCasesYesterday;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLatestConfirmedCases() {
        return LatestConfirmedCases;
    }

    public void setLatestConfirmedCases(int latestConfirmedCases) {
        LatestConfirmedCases = latestConfirmedCases;
    }
}
