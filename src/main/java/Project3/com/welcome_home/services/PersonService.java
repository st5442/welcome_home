package Project3.com.welcome_home.services;

import Project3.com.welcome_home.entities.Person;
import Project3.com.welcome_home.repositories.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    public Person getPerson(String userName) {
        return personRepository.findById(userName).orElse(null);
    }
}
