package Project3.com.welcome_home.controllers;

import Project3.com.welcome_home.commons.DeliveryStatus;
import Project3.com.welcome_home.commons.PersonRoles;
import Project3.com.welcome_home.model.DeliveryStatusDT;
import Project3.com.welcome_home.model.UserDetails;
import Project3.com.welcome_home.repositories.DeliveredRepository;
import Project3.com.welcome_home.services.DeliveredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/delivered")
public class DeliveredController {

    @Autowired
    private DeliveredService deliveredService;
    @Autowired
    private UserDetails userDetails;

    @PostMapping("/changeDeliveryStatus")
    public ResponseEntity<?> changeDeliveryStatus(@RequestBody DeliveryStatusDT deliveryStatusDT) {
        if(!(userDetails.getRoleID().equals(PersonRoles.SUPERVISOR.toString()) || userDetails.getRoleID().equals(PersonRoles.DELIVERY.toString()) ||
                userDetails.getRoleID().equals(PersonRoles.STAFF.toString()))) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        deliveredService.changeStatusOfDelivery(deliveryStatusDT.getOrderID(),
                DeliveryStatus.valueOf(deliveryStatusDT.getStatus()));
        return ResponseEntity.status(HttpStatus.OK).body("Updated.");
    }


}
