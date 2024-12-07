package Project3.com.welcome_home.controllers;

import Project3.com.welcome_home.entities.Location;
import Project3.com.welcome_home.services.LocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public List<Location> getAllLocations() {
        return locationService.getAllLocations();
    }

    @PostMapping
    public Location createLocation(@RequestBody Location location) {
        return locationService.saveLocation(location);
    }

    @GetMapping("/{roomNum}/{shelfNum}")
    public Location getLocation(@PathVariable Integer roomNum, @PathVariable Integer shelfNum) {
        return locationService.getLocation(roomNum, shelfNum);
    }
}
