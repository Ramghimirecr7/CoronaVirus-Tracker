package com.example.CoronavirusTracker.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.CoronavirusTracker.model.LocationFacts;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronavirusDataService {
    public static String url_virus_data="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private  List<LocationFacts> location = new ArrayList<>();

    public List<LocationFacts> getLocation() {
        return location;
    }

    @PostConstruct
    @Scheduled(cron =" * * 1 * * *") //run this method every day for the first hour
    public void VirusData () throws IOException, InterruptedException {
        List<LocationFacts> newLocation = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request1 = HttpRequest.newBuilder().uri(URI.create(url_virus_data)).build();

        HttpResponse<String> response1 = client.send(request1, HttpResponse.BodyHandlers.ofString());

        StringReader stringReader1 = new StringReader(response1.body());

        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(stringReader1);
        for (CSVRecord record : records) {
            LocationFacts locationFacts = new LocationFacts();

            locationFacts.setState(record.get("Province/State"));
            locationFacts.setCountry(record.get("Country/Region"));
            locationFacts.setLatestConfirmedCases(Integer.parseInt(record.get(record.size()-1)));

            int latestCases = Integer.parseInt(record.get(record.size()-1));
            int pastCases = Integer.parseInt(record.get(record.size()-2));
            int newCases = latestCases-pastCases;

            locationFacts.setNewCasesYesterday(newCases);
            newLocation.add(locationFacts);
        }
        this.location=newLocation;
    }
}
