package Project3.com.welcome_home.controllers;

import Project3.com.welcome_home.entities.ItemIn;
import Project3.com.welcome_home.services.ItemInService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items-in")
public class ItemInController {

    private final ItemInService itemInService;

    public ItemInController(ItemInService itemInService) {
        this.itemInService = itemInService;
    }

    @GetMapping
    public List<ItemIn> getAllItemIns() {
        return itemInService.getAllItemIns();
    }

    @PostMapping
    public ItemIn createItemIn(@RequestBody ItemIn itemIn) {
        return itemInService.saveItemIn(itemIn);
    }
}
