package Project3.com.welcome_home.controllers;

import Project3.com.welcome_home.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/{orderID}/items")
    public ResponseEntity<List<Map<String, Object>>> getOrderItemsWithLocations(@PathVariable Integer orderID) {
        List<Map<String, Object>> result = orderService.getOrderItemsWithLocations(orderID);

        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(result);
    }
}
