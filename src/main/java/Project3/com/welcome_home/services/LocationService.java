package Project3.com.welcome_home.services;

import Project3.com.welcome_home.entities.Location;
import Project3.com.welcome_home.entities.LocationId;
import Project3.com.welcome_home.repositories.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Optional<Location> getLocation(int roomNum, int shelfNum) {
        // Create a LocationId object
        LocationId id = new LocationId(roomNum, shelfNum);
        return locationRepository.findById(id);
    }

    public Location saveLocation(Location location) {
        return locationRepository.save(location);
    }

    public void deleteLocation(int roomNum, int shelfNum) {
        // Create a LocationId object
        LocationId id = new LocationId(roomNum, shelfNum);
        locationRepository.deleteById(id);
    }
}
