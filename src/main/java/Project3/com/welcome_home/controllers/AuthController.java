package Project3.com.welcome_home.controllers;

import Project3.com.welcome_home.entities.Person;
import Project3.com.welcome_home.model.RegisterPersonDT;
import Project3.com.welcome_home.services.PersonService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final PersonService personService;

    public HttpSession getSession() {
        return this.session;
    }

    private HttpSession session;

    public AuthController(PersonService personService) {
        this.personService = personService;
    }

    // Register a new user
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterPersonDT person) {
        Map<Boolean, String> map = personService.registerUser(person);
        if(map.containsKey(true)) {
            return ResponseEntity.ok("User registered successfully");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map.get(false));
    }

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String userName, @RequestParam String password, HttpSession session) {
        // Fetch full person entity including password
        Optional<Person> personOptional = personService.findFullPersonByUserName(userName);

        // Check if the user exists
        if (personOptional.isPresent()) {
            Person person = personOptional.get();

            // Validate the password using the method from PersonService
            if (personService.validatePassword(password, person.getPassword())) {
                // Successful login, create session attributes
                session.setAttribute("user", person.getUserName());
                session.setAttribute("role", person.getRoleID()); // assuming role is set in Person entity
                this.session = session;
                return ResponseEntity.ok("Login successful");
            } else {
                // Incorrect password
                return ResponseEntity.status(401).body("Invalid credentials");
            }
        } else {
            // User not found
            return ResponseEntity.status(404).body("User not found");
        }
    }


    // Logout the user
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        this.session = session;// Destroy session
        return ResponseEntity.ok("Logged out successfully");
    }
}
