package Project3.com.welcome_home.services;

import Project3.com.welcome_home.entities.Delivered;
import Project3.com.welcome_home.repositories.DeliveredRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveredService {

    private final DeliveredRepository deliveredRepository;

    public DeliveredService(DeliveredRepository deliveredRepository) {
        this.deliveredRepository = deliveredRepository;
    }

    public List<Delivered> getAllDeliveredItems() {
        return deliveredRepository.findAll();
    }

    public Delivered saveDelivered(Delivered delivered) {
        return deliveredRepository.save(delivered);
    }

    public Delivered getDelivered(String userName, Integer orderId) {
        return deliveredRepository.findById(new DeliveredId(userName, orderId)).orElse(null);
    }
}
