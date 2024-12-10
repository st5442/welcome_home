package Project3.com.welcome_home.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import Project3.com.welcome_home.services.OrderService;
import Project3.com.welcome_home.model.UserDetails;
import org.springframework.web.bind.annotation.SessionAttribute;


@Controller
public class PageController {

    @Autowired
    private OrderService orderService;  // Ensure it's injected

    @Autowired
    private UserDetails userDetails;


    @GetMapping("/register")
    public String showRegisterPage() {
        return "register"; // Maps to register.html
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // Maps to login.html
    }

    @GetMapping("/dashboard")
    public String showDashboardPage() {
        return "dashboard";  // This will map to dashboard.html in templates folder
    }

    @GetMapping("/donor-dashboard")
    public String showDonorDashboardPage() {
        return "donor-dashboard";  // This will map to dashboard.html in templates folder
    }


    // Mapping for the page to find order items
    @GetMapping("/findOrderItems")
    public String showFindOrderItemsPage(Model model) {
        // You can pass any data to the model if needed
        // model.addAttribute("someAttribute", someValue);
        return "findOrderItems"; // Returns the 'findOrderItems.html' page
    }

    @GetMapping("/find-item")
    public String findItemPage() {
        return "find-item";  // This will map to dashboard.html in templates folder
    }

    @GetMapping("/start")
    public String startOrderPage() {
        return "start-order";  // This will map to dashboard.html in templates folder
    }

    @GetMapping("/donate")
    public String donatePage() {
        return "donate";  // This will map to dashboard.html in templates folder
    }

    @GetMapping("/prepare-order")
    public String prepareOrderPage() {
        return "prepare-order";  // This will map to dashboard.html in templates folder
    }

    @GetMapping("/add-to-order")
    public String showAddToOrderPage(HttpSession session, Model model) {
        return "addToOrder";
    }
    @GetMapping("/show-orders")
    public String showOrdersPage(Model model) {
        return "show-orders";  // This will map to dashboard.html in templates folder
    }
}




