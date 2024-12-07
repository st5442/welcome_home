package Project3.com.welcome_home.controllers;

import Project3.com.welcome_home.entities.Person;
import Project3.com.welcome_home.services.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        return personService.savePerson(person);
    }

    @GetMapping("/{userName}")
    public Person getPerson(@PathVariable String userName) {
        return personService.getPerson(userName);
    }
}
