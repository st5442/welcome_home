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
}
