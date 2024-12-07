package Project3.com.welcome_home.services;

import Project3.com.welcome_home.entities.ItemIn;
import Project3.com.welcome_home.repositories.ItemInRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemInService {

    private final ItemInRepository itemInRepository;

    public ItemInService(ItemInRepository itemInRepository) {
        this.itemInRepository = itemInRepository;
    }

    public List<ItemIn> getAllItemIns() {
        return itemInRepository.findAll();
    }

    public ItemIn saveItemIn(ItemIn itemIn) {
        return itemInRepository.save(itemIn);
    }

    public ItemIn getItemIn(Integer itemId, Integer orderId) {
        return itemInRepository.findById(new ItemInId(itemId, orderId)).orElse(null);
    }
}
