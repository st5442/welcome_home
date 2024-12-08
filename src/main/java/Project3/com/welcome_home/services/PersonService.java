package Project3.com.welcome_home.services;

import Project3.com.welcome_home.entities.Person;
import Project3.com.welcome_home.entities.Act;
import Project3.com.welcome_home.repositories.PersonRepository;
import Project3.com.welcome_home.repositories.ActRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final ActRepository actRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public PersonService(PersonRepository personRepository, ActRepository actRepository) {
        this.personRepository = personRepository;
        this.actRepository = actRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // Register a user (salt and hash the password)
    public Person registerUser(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));  // Hash the password before saving
        return personRepository.save(person);
    }

    // Find user by username
    public Optional<Person> findFullPersonByUserName(String userName) {
        return personRepository.findFullPersonByUserName(userName);
    }

    // Validate the entered password with the stored hashed password
    public boolean validatePassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    // Get all persons
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    // Fetch roles associated with the user
    public Set<Act> getRolesByUserName(String userName) {
        Optional<Person> personOpt = personRepository.findFullPersonByUserName(userName);
        return personOpt.map(Person::getRoles).orElse(null);
    }
}
