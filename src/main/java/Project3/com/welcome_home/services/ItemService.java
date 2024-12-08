package Project3.com.welcome_home.services;

import Project3.com.welcome_home.entities.Item;
import Project3.com.welcome_home.repositories.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    public Item getItemById(Integer itemId) {
        return itemRepository.findById(itemId).orElse(null);
    }
}
