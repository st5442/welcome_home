package Project3.com.welcome_home.controllers;

import Project3.com.welcome_home.entities.DonatedBy;
import Project3.com.welcome_home.services.DonatedByService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donations")
public class DonatedByController {

    private final DonatedByService donatedByService;

    public DonatedByController(DonatedByService donatedByService) {
        this.donatedByService = donatedByService;
    }

    @GetMapping
    public List<DonatedBy> getAllDonations() {
        return donatedByService.getAllDonations();
    }

    @PostMapping
    public DonatedBy createDonation(@RequestBody DonatedBy donatedBy) {
        return donatedByService.saveDonation(donatedBy);
    }

    @GetMapping("/{itemId}/{userName}")
    public DonatedBy getDonation(@PathVariable Integer itemId, @PathVariable String userName) {
        return donatedByService.getDonation(itemId, userName);
    }
}
