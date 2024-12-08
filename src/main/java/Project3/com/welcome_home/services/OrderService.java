package Project3.com.welcome_home.services;

import Project3.com.welcome_home.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.JdbcTemplate;
import Project3.com.welcome_home.services.ItemDetails;


import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

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

    // Method to prepare an order
    // Prepare an order (set items to "holding" location and mark them as unavailable)
    @Transactional
    public String prepareOrder(int orderID, String clientUsername) {
        // Step 1: Fetch items for the given orderID or clientUsername
        String findOrderSql = "SELECT o.orderID, ii.ItemID, p.pieceNum, l.roomNum, l.shelfNum " +
                "FROM `Ordered` o " +
                "JOIN ItemIn ii ON o.orderID = ii.orderID " +
                "JOIN Piece p ON ii.ItemID = p.ItemID " +
                "JOIN Location l ON p.roomNum = l.roomNum AND p.shelfNum = l.shelfNum " +
                "WHERE o.orderID = ? OR o.client = ?";

        // Query items for the order or client
        List<ItemDetails> items = jdbcTemplate.query(findOrderSql, new Object[]{orderID, clientUsername}, (rs, rowNum) -> {
            ItemDetails item = new ItemDetails();
            item.setItemID(rs.getInt("ItemID"));
            item.setPieceNum(rs.getInt("pieceNum"));
            item.setRoomNum(rs.getInt("roomNum"));
            item.setShelfNum(rs.getInt("shelfNum"));
            return item;
        });

        // If no items are found, return an error message
        if (items.isEmpty()) {
            return "Error: No items found for orderID or clientUsername.";
        }

        // Step 2: Update item locations to "holding location"
        String holdingLocationSql = "UPDATE PieceIn SET roomNum = ?, shelfNum = ?, notes = 'Ready for delivery' WHERE ItemID = ? AND pieceNum = ?";
        for (ItemDetails item : items) {
            // Assuming holding location is room 99, shelf 99 (adjust as needed)
            jdbcTemplate.update(holdingLocationSql, 99, 99, item.getItemID(), item.getPieceNum());
        }

        // Step 3: Mark items as unavailable for clients
        String markAsUnavailableSql = "UPDATE Item SET isNew = FALSE WHERE ItemID = ?";
        for (ItemDetails item : items) {
            jdbcTemplate.update(markAsUnavailableSql, item.getItemID());
        }

        // Return success message
        return "Order " + orderID + " is ready for delivery.";
    }
}

