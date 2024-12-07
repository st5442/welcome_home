package Project3.com.welcome_home.services;

import Project3.com.welcome_home.entities.ItemIn;
import Project3.com.welcome_home.entities.ItemInId;
import Project3.com.welcome_home.repositories.ItemInRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemInService {

    private final ItemInRepository itemInRepository;

    public ItemInService(ItemInRepository itemInRepository) {
        this.itemInRepository = itemInRepository;
    }

    public List<ItemIn> getAllItemIns() {
        return itemInRepository.findAll();
    }

    public Optional<ItemIn> getItemIn(Integer itemID, Integer orderID) {
        // Create ItemInId object
        ItemInId id = new ItemInId(itemID, orderID);
        return itemInRepository.findById(id);
    }

    public ItemIn saveItemIn(ItemIn itemIn) {
        return itemInRepository.save(itemIn);
    }

    public void deleteItemIn(Integer itemID, Integer orderID) {
        // Create ItemInId object
        ItemInId id = new ItemInId(itemID, orderID);
        itemInRepository.deleteById(id);
    }
}
