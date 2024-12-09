package Project3.com.welcome_home.controllers;

import Project3.com.welcome_home.services.VolunteerRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class VolunteerRankController {

    @Autowired
    private VolunteerRankService volunteerRankService;

    @GetMapping("/api/volunteer/rank")
    public List<Map<String, Object>> getVolunteerRankings(
            @RequestParam String startDate,
            @RequestParam String endDate) {
        return volunteerRankService.getVolunteerRankings(startDate, endDate);
    }
}