package Project3.com.welcome_home.services;

import Project3.com.welcome_home.commons.Constants;
import Project3.com.welcome_home.commons.PersonRoles;
import Project3.com.welcome_home.entities.Location;
import Project3.com.welcome_home.entities.Person;
import Project3.com.welcome_home.model.PrepareOrderDT;
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

    public List<Map<String, Object>> showOrders(String userName, String roleID) {
        if(roleID.equals(PersonRoles.DONOR.toString())) {
            return orderRepository.findOrderItemsForClient(userName);
        } else if(roleID.equals(PersonRoles.SUPERVISOR.toString()) ||  roleID.equals(PersonRoles.STAFF.toString()) || roleID.equals(PersonRoles.DELIVERY.toString())){
            return orderRepository.findOrderItemsForSupervisor(userName);
        }
        return null;
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
    public String prepareOrder(PrepareOrderDT orderDT) {
        // Step 1: Fetch items for the given orderID or clientUsername
        String findOrderSql = null;
        List<ItemDetails> items = null;
        Boolean hasPiece;
        if(orderDT.getUserName() != null) {

            String findPiecesSQL = "SELECT i.hasPieces FROM " +
                    "Item i " +
                    "JOIN DonatedBy d " +
                    "ON i.itemID = d.ItemID " +
                    "WHERE d.userName = ?";

            List<Boolean> hasPieces = jdbcTemplate.query(findPiecesSQL, new Object[]{orderDT.getUserName()}, (rs, rowNum) -> {
                return rs.getBoolean("hasPieces");
            });

            hasPiece = hasPieces.get(0);

            if(hasPiece) {
                findOrderSql = "SELECT ii.orderID, ii.ItemID, p.pieceNum, p.roomNum, p.shelfNum " +
                        "FROM ItemIn ii " +
                        "JOIN Piece p ON ii.ItemID = p.ItemID " +
                        "JOIN DonatedBy d ON d.ItemID = ii.ItemID " +
                        "WHERE d.userName = ?";
                items = jdbcTemplate.query(findOrderSql, new Object[]{orderDT.getOrderID()}, (rs, rowNum) -> {
                    ItemDetails item = new ItemDetails();
                    item.setItemID(rs.getInt("ItemID"));
                    item.setPieceNum(rs.getInt("pieceNum"));
                    item.setRoomNum(rs.getInt("roomNum"));
                    item.setShelfNum(rs.getInt("shelfNum"));
                    return item;
                });
            } else {
                findOrderSql = "SELECT ii.orderID, ii.ItemID " +
                        "FROM ItemIn ii " +
                        "JOIN DonatedBy d ON d.ItemID = ii.ItemID " +
                        "WHERE d.userName = ?";
                items = jdbcTemplate.query(findOrderSql, new Object[]{orderDT.getUserName()}, (rs, rowNum) -> {
                    ItemDetails item = new ItemDetails();
                    item.setItemID(rs.getInt("ItemID"));
                    item.setPieceNum(-1);
                    item.setRoomNum(-1);
                    item.setShelfNum(-1);
                    return item;
                });
            }
        } else {
            String findPiecesSQL = "SELECT i.hasPieces FROM " +
                    "Item i JOIN ItemIn ii " +
                    "ON i.itemID = ii.ItemID " +
                    "WHERE ii.orderID = ?";

            List<Boolean> hasPieces = jdbcTemplate.query(findPiecesSQL, new Object[]{orderDT.getOrderID()}, (rs, rowNum) -> {
                return rs.getBoolean("hasPieces");
            });

            hasPiece = hasPieces.get(0);
            if(hasPiece) {
                findOrderSql = "SELECT o.orderID, ii.ItemID, p.pieceNum, l.roomNum, l.shelfNum " +
                        "FROM Ordered o " +
                        "JOIN ItemIn ii ON o.orderID = ii.orderID " +
                        "JOIN Piece p ON ii.ItemID = p.ItemID " +
                        "JOIN Location l ON p.roomNum = l.roomNum AND p.shelfNum = l.shelfNum " +
                        "WHERE o.orderID = ?";
                items = jdbcTemplate.query(findOrderSql, new Object[]{orderDT.getOrderID()}, (rs, rowNum) -> {
                    ItemDetails item = new ItemDetails();
                    item.setItemID(rs.getInt("ItemID"));
                    item.setPieceNum(rs.getInt("pieceNum"));
                    item.setRoomNum(rs.getInt("roomNum"));
                    item.setShelfNum(rs.getInt("shelfNum"));
                    return item;
                });
            } else {
                findOrderSql = "SELECT o.orderID, ii.ItemID " +
                        "FROM Ordered o " +
                        "JOIN ItemIn ii ON o.orderID = ii.orderID " +
                        "WHERE o.orderID = ?";
                    items = jdbcTemplate.query(findOrderSql, new Object[]{orderDT.getOrderID()}, (rs, rowNum) -> {
                        ItemDetails item = new ItemDetails();
                        item.setItemID(rs.getInt("ItemID"));
                        item.setPieceNum(-1);
                        item.setRoomNum(-1);
                        item.setShelfNum(-1);
                        return item;
                    });
            }


        }

        // If no items are found, return an error message
        if (items.isEmpty()) {
            return "Error: No items found for orderID or clientUsername.";
        }

        // Step 2: Update item locations to "holding location"
        String checkLocation = "Select * from Location where roomNum = ? and shelfNum = ?";
        String addnewLocation = "INSERT INTO Location VALUES (?, ?, \'Shelf99\')";
        List<Location> locations = jdbcTemplate.query(checkLocation, new Object[]{99, 99}, (rs, rowNum) -> {
            Location location = new Location();
            location.setRoomNum(rs.getInt("roomNum"));
            location.setShelfNum(rs.getInt("shelfNum"));
            location.setShelfDescription(rs.getString("shelfDescription"));
            return location;
        });
        if(locations.isEmpty()) {
            jdbcTemplate.execute(addnewLocation);
        }
        if(hasPiece){
            String holdingLocationSql = "UPDATE Piece SET roomNum = ?, shelfNum = ?, pNotes = 'Ready for delivery' WHERE ItemID = ? AND pieceNum = ?";
            for (ItemDetails item : items) {
                // Assuming holding location is room 99, shelf 99 (adjust as needed)
                jdbcTemplate.update(holdingLocationSql, 99, 99, item.getItemID(), item.getPieceNum());
            }
        }


        // Step 3: Mark items as unavailable for clients
        String markAsUnavailableSql = "UPDATE Item SET isNew = FALSE WHERE ItemID = ?";
        for (ItemDetails item : items) {
            jdbcTemplate.update(markAsUnavailableSql, item.getItemID());
        }

        // Return success message
        return "Order " + orderDT.getOrderID() + " is ready for delivery.";
    }
}

