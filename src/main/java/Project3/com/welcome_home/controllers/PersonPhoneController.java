package Project3.com.welcome_home.controllers;

import Project3.com.welcome_home.entities.PersonPhone;
import Project3.com.welcome_home.services.PersonPhoneService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personPhones")
public class PersonPhoneController {

    private final PersonPhoneService personPhoneService;

    public PersonPhoneController(PersonPhoneService personPhoneService) {
        this.personPhoneService = personPhoneService;
    }

    @GetMapping
    public List<PersonPhone> getAllPersonPhones() {
        return personPhoneService.getAllPersonPhones();
    }

    @PostMapping
    public PersonPhone createPersonPhone(@RequestBody PersonPhone personPhone) {
        return personPhoneService.savePersonPhone(personPhone);
    }

    @GetMapping("/{userName}/{phone}")
    public PersonPhone getPersonPhone(@PathVariable String userName, @PathVariable String phone) {
        return personPhoneService.getPersonPhone(userName, phone);
    }
}
