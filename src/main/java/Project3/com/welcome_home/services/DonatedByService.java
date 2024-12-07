package Project3.com.welcome_home.services;

import Project3.com.welcome_home.entities.DonatedBy;
import Project3.com.welcome_home.repositories.DonatedByRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonatedByService {

    private final DonatedByRepository donatedByRepository;

    public DonatedByService(DonatedByRepository donatedByRepository) {
        this.donatedByRepository = donatedByRepository;
    }

    public List<DonatedBy> getAllDonations() {
        return donatedByRepository.findAll();
    }

    public DonatedBy saveDonation(DonatedBy donatedBy) {
        return donatedByRepository.save(donatedBy);
    }

    public DonatedBy getDonation(Integer itemId, String userName) {
        return donatedByRepository.findById(new DonatedById(itemId, userName)).orElse(null);
    }
}
