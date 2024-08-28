package com.example.wildberriesapi.service.analytics.nm_report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AnalyticWebController {

    AnalyticService analyticService;

    @Autowired
    private AnalyticWebController(AnalyticService analyticService) {
        this.analyticService = analyticService;
    }

    @GetMapping("/nm-report")
    public String getAnalytics(@RequestParam List<String> downloadsIds, Model model) {
        AnalyticResponse analyticResponse = analyticService.getReports(downloadsIds);
        model.addAttribute("analytics", analyticResponse);
        return "nm-report";
    }

}
