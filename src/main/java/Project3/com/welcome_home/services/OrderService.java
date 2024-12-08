package Project3.com.welcome_home.services;

import Project3.com.welcome_home.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Map<String, Object>> getOrderItemsWithLocations(Integer orderID) {
        return orderRepository.findOrderItemsWithLocations(orderID);
    }
}
