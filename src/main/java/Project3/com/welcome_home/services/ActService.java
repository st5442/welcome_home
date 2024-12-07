package Project3.com.welcome_home.services;

import Project3.com.welcome_home.entities.Act;
import Project3.com.welcome_home.entities.ActId;
import Project3.com.welcome_home.repositories.ActRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActService {

    private final ActRepository actRepository;

    public ActService(ActRepository actRepository) {
        this.actRepository = actRepository;
    }

    public List<Act> getAllActs() {
        return actRepository.findAll();
    }

    public Optional<Act> getAct(String userName, String roleID) {
        // Create ActId object
        ActId id = new ActId(userName, roleID);
        return actRepository.findById(id);
    }

    public Act saveAct(Act act) {
        return actRepository.save(act);
    }

    public void deleteAct(String userName, String roleID) {
        // Create ActId object
        ActId id = new ActId(userName, roleID);
        actRepository.deleteById(id);
    }
}
