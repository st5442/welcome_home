package Project3.com.welcome_home.controllers;

import Project3.com.welcome_home.services.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/start")
    public String startOrder(@RequestParam String loggedInUser,
                             @RequestParam String clientUserName,
                             @RequestParam String orderNotes,
                             HttpSession session) {
        try {
            // Check if the logged-in user is staff
            if (!orderService.isStaff(loggedInUser)) {
                return "Error: Only staff can start an order.";
            }

            // Check if the client username exists
            if (!orderService.isClientValid(clientUserName)) {
                return "Error: Invalid client username.";
            }

            // Start the order
            int orderId = orderService.startOrder(loggedInUser, clientUserName, orderNotes);

            // Save the order ID in the session
            session.setAttribute("orderId", orderId);
            return "Order started successfully. Order ID: " + orderId;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
