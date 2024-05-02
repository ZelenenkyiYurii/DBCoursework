package com.example.courseworkdb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/statistics")
public class StatisticsController {

    @GetMapping()
    public String getStatistic(Model model){
        model.addAttribute("data", new int[]{10, 20, 30, 40, 50});
        return "views/admin_page/statisticPage";
    }

}
