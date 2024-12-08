package Project3.com.welcome_home.services;

import Project3.com.welcome_home.commons.Constants;
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

    public List<Map<String, Object>> showOrders(String userName, String roleID) {
        if(roleID.equals(Constants.donorKey)){
            return orderRepository.findOrderItemsForClient(userName);
        } else if(roleID.equals(Constants.staffKey)){
            return orderRepository.findOrderItemsForSupervisor(userName);
        }
        return null;
    }
}
