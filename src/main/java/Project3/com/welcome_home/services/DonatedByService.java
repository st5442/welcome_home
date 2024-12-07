package Project3.com.welcome_home.services;

import Project3.com.welcome_home.entities.DonatedBy;
import Project3.com.welcome_home.entities.DonatedById;
import Project3.com.welcome_home.repositories.DonatedByRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonatedByService {

    private final DonatedByRepository donatedByRepository;

    public DonatedByService(DonatedByRepository donatedByRepository) {
        this.donatedByRepository = donatedByRepository;
    }

    public List<DonatedBy> getAllDonations() {
        return donatedByRepository.findAll();
    }

    public Optional<DonatedBy> getDonation(Integer itemID, String userName) {
        // Create DonatedById object
        DonatedById id = new DonatedById(itemID, userName);
        return donatedByRepository.findById(id);
    }

    public DonatedBy saveDonation(DonatedBy donatedBy) {
        return donatedByRepository.save(donatedBy);
    }

    public void deleteDonation(Integer itemID, String userName) {
        // Create DonatedById object
        DonatedById id = new DonatedById(itemID, userName);
        donatedByRepository.deleteById(id);
    }
}
