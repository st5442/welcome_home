package Project3.com.welcome_home.controllers;

import Project3.com.welcome_home.entities.Act;
import Project3.com.welcome_home.services.ActService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/acts")
public class ActController {

    private final ActService actService;

    public ActController(ActService actService) {
        this.actService = actService;
    }

    @GetMapping
    public List<Act> getAllActs() {
        return actService.getAllActs();
    }

    @PostMapping
    public Act createAct(@RequestBody Act act) {
        return actService.saveAct(act);
    }

    @GetMapping("/{userName}/{roleID}")
    public Act getAct(@PathVariable String userName, @PathVariable String roleID) {
        return actService.getAct(userName, roleID);
    }
}
