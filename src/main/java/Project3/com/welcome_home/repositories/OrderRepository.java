package Project3.com.welcome_home.repositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class OrderRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> findOrderItemsWithLocations(Integer orderID) {
        String sql = """
            SELECT 
                i.ItemID,
                i.iDescription,
                p.pieceNum,
                p.pDescription,
                l.roomNum,
                l.shelfNum,
                l.shelfDescription
            FROM 
                ItemIn ii
            JOIN 
                Item i ON ii.ItemID = i.ItemID
            LEFT JOIN 
                Piece p ON i.ItemID = p.ItemID
            LEFT JOIN 
                Location l ON p.roomNum = l.roomNum AND p.shelfNum = l.shelfNum
            WHERE 
                ii.orderID = ?;
        """;

        return jdbcTemplate.queryForList(sql, orderID);
    }

    // Check if the logged-in user is a staff member
    public boolean isStaff(String userName) {
        String sql = "SELECT COUNT(*) FROM Act WHERE userName = ? AND roleID = 'STAFF'";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, userName);
        return count != null && count > 0;
    }

    // Check if the client username exists
    public boolean isClientValid(String clientUserName) {
        String sql = "SELECT COUNT(*) FROM Person WHERE userName = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, clientUserName);
        return count != null && count > 0;
    }

    // Insert a new order
    public void insertOrder(String orderNotes, String supervisor, String clientUserName) {
        String sql = "INSERT INTO Ordered (orderDate, orderNotes, supervisor, client) VALUES (CURDATE(), ?, ?, ?)";
        jdbcTemplate.update(sql, orderNotes, supervisor, clientUserName);
    }

    // Retrieve the last inserted order ID
    public int getLastOrderId() {
        String sql = "SELECT LAST_INSERT_ID() AS orderID";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
