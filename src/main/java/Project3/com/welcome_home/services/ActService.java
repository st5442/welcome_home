package Project3.com.welcome_home.services;

import Project3.com.welcome_home.entities.Act;
import Project3.com.welcome_home.repositories.ActRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActService {

    private final ActRepository actRepository;

    public ActService(ActRepository actRepository) {
        this.actRepository = actRepository;
    }

    public List<Act> getAllActs() {
        return actRepository.findAll();
    }

    public Act saveAct(Act act) {
        return actRepository.save(act);
    }

    public Act getAct(String userName, String roleID) {
        return actRepository.findById(new ActId(userName, roleID)).orElse(null);
    }
}
