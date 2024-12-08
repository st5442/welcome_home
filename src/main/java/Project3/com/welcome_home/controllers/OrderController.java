package Project3.com.welcome_home.controllers;

import Project3.com.welcome_home.commons.Constants;
import Project3.com.welcome_home.model.UserDetails;
import Project3.com.welcome_home.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserDetails userDetails;

    @GetMapping("/{orderID}/items")
    public ResponseEntity<List<Map<String, Object>>> getOrderItemsWithLocations(@PathVariable Integer orderID) {
        List<Map<String, Object>> result = orderService.getOrderItemsWithLocations(orderID);

        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/showOrders")
    public ResponseEntity<List<Map<String, Object>>> showOrders() {
        String userName = userDetails.getUserName();
        String roleID = userDetails.getRoleID();
        List<Map<String, Object>> result = orderService.showOrders(userName, roleID);
        if(result == null || result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(result);
    }
}
