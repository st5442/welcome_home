package Project3.com.welcome_home.services;

import Project3.com.welcome_home.entities.Location;
import Project3.com.welcome_home.repositories.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Location saveLocation(Location location) {
        return locationRepository.save(location);
    }

    public Location getLocation(Integer roomNum, Integer shelfNum) {
        return locationRepository.findById(new LocationId(roomNum, shelfNum)).orElse(null);
    }
}
