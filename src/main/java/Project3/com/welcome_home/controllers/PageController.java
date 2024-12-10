package Project3.com.welcome_home.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

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
    @GetMapping("/find-item")
    public String findItemPage() {
        return "find-item";  // This will map to dashboard.html in templates folder
    }
}
