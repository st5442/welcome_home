package Project3.com.welcome_home.services;

import Project3.com.welcome_home.entities.Person;
import Project3.com.welcome_home.entities.Act;
import Project3.com.welcome_home.model.RegisterPersonDT;
import Project3.com.welcome_home.repositories.PersonRepository;
import Project3.com.welcome_home.repositories.ActRepository;
import Project3.com.welcome_home.repositories.RoleRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final ActRepository actRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    @Autowired
    public PersonService(PersonRepository personRepository, ActRepository actRepository, RoleRepository roleRepository) {
        this.personRepository = personRepository;
        this.actRepository = actRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.roleRepository = roleRepository;
    }

    // Register a user (salt and hash the password)
    @Transactional
    public Map<Boolean, String> registerUser(RegisterPersonDT person) {
        HashMap<Boolean, String> map = new HashMap<>();

        // Check if the username already exists
        if (personRepository.findByUserName(person.getUserName()).isPresent()) {
            map.put(false, "Username already exists");
            return map;
        }

        // Create a new Person object and hash the password
        Person newPerson = new Person();
        newPerson.setUserName(person.getUserName());
        newPerson.setPassword(passwordEncoder.encode(person.getPassword()));  // Hash the password before saving
        newPerson.setEmail(person.getEmail());
        newPerson.setFname(person.getFname());
        newPerson.setLname(person.getLname());

        // Save the person
        personRepository.save(newPerson);

        // Create and assign a new Act (role assignment)
        Act newAct = new Act();
        newAct.setUserName(newPerson.getUserName());
        newAct.setRoleID(person.getRole());
        newAct.setPerson(newPerson);
        newAct.setRole(roleRepository.findById(person.getRole()).orElseThrow(() -> new IllegalArgumentException("Role not found")));

        // Save the Act
        actRepository.save(newAct);

        map.put(true, "Successfully registered.");
        return map;
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
