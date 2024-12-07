package Project3.com.welcome_home.services;

import Project3.com.welcome_home.entities.PersonPhone;
import Project3.com.welcome_home.repositories.PersonPhoneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonPhoneService {

    private final PersonPhoneRepository personPhoneRepository;

    public PersonPhoneService(PersonPhoneRepository personPhoneRepository) {
        this.personPhoneRepository = personPhoneRepository;
    }

    public List<PersonPhone> getAllPersonPhones() {
        return personPhoneRepository.findAll();
    }

    public PersonPhone savePersonPhone(PersonPhone personPhone) {
        return personPhoneRepository.save(personPhone);
    }

    public PersonPhone getPersonPhone(String userName, String phone) {
        return personPhoneRepository.findById(new PersonPhoneId(userName, phone)).orElse(null);
    }
}
