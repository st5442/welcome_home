package Project3.com.welcome_home.services;

import Project3.com.welcome_home.commons.DeliveryStatus;
import Project3.com.welcome_home.entities.Delivered;
import Project3.com.welcome_home.entities.DeliveredId;
import Project3.com.welcome_home.repositories.DeliveredRepository;
import Project3.com.welcome_home.repositories.DeliveredRepositoryJDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DeliveredService {

    @Autowired
    private DeliveredRepositoryJDBC deliveredRepository;

    public void changeStatusOfDelivery(Integer orderID, DeliveryStatus deliveryStatus) {
        deliveredRepository.changeStatusOfDelivery(orderID, deliveryStatus);
    }
}
