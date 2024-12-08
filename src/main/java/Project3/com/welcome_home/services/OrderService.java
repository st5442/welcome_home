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

    // Check if the logged-in user is a staff member
    public boolean isStaff(String userName) {
        return orderRepository.isStaff(userName);
    }

    // Check if the client username exists
    public boolean isClientValid(String clientUserName) {
        return orderRepository.isClientValid(clientUserName);
    }

    // Start the order and return the order ID
    public int startOrder(String supervisor, String clientUserName, String orderNotes) {
        // Insert the new order
        orderRepository.insertOrder(orderNotes, supervisor, clientUserName);

        // Retrieve and return the order ID
        return orderRepository.getLastOrderId();
    }

}
