package com.example.CoronavirusTracker.controllers;

import com.example.CoronavirusTracker.model.LocationFacts;
import com.example.CoronavirusTracker.service.CoronavirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class controller {

    @Autowired
    CoronavirusDataService coronavirusDataService;


    @GetMapping("/")
    public String home(Model model){
        List<LocationFacts> allstat = coronavirusDataService.getLocation();
        int TotalCoronavirusCases = allstat.stream().mapToInt(stat->stat.getLatestConfirmedCases()).sum();
        int TotalnewCoronavirusCases = allstat.stream().mapToInt(stat->stat.getNewCasesYesterday()).sum();

        model.addAttribute("locationFacts", allstat);
        model.addAttribute("TotalCoronavirusCases",TotalCoronavirusCases);
        model.addAttribute("TotalnewCoronavirusCases",TotalnewCoronavirusCases);

        return "home";
    }
}
