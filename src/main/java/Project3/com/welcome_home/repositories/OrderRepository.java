package Project3.com.welcome_home.repositories;
import Project3.com.welcome_home.entities.Ordered;
import Project3.com.welcome_home.services.ItemDetails;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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


    public List<Map<String, Object>> findOrderItemsForClient(String userName) {
        String sql = """
                select 
                    ii.ItemID,
                    i.iDescription,
                    ii.orderID 
                from 
                    itemin ii 
                join 
                        ordered o 
                on 
                    ii.orderID = o.orderID 
                join 
                        item i 
                on 
                    ii.ItemID = i.ItemID
                where 
                    o.client = ?;
                """;
        return jdbcTemplate.queryForList(sql, userName);
    }

    public List<Map<String, Object>> findOrderItemsForSupervisor(String userName) {
        String sql = """
                select 
                    ii.ItemID, i.iDescription, ii.orderID 
                from 
                    itemin ii 
                join 
                        ordered o 
                on 
                    ii.orderID = o.orderID 
                join 
                        item i 
                on 
                    ii.ItemID = i.ItemID
                where 
                    o.supervisor = ?;
                """;
        return jdbcTemplate.queryForList(sql, userName);
    }
        // Check if the logged-in user is a staff member
        public boolean isStaff (String userName){
            String sql = "SELECT COUNT(*) FROM Act WHERE userName = ? AND roleID = 'STAFF'";
            Integer count = jdbcTemplate.queryForObject(sql, Integer.class, userName);
            return count != null && count > 0;
        }

        // Check if the client username exists
        public boolean isClientValid (String clientUserName){
            String sql = "SELECT COUNT(*) FROM Person WHERE userName = ?";
            Integer count = jdbcTemplate.queryForObject(sql, Integer.class, clientUserName);
            return count != null && count > 0;
        }

        // Insert a new order
        @SneakyThrows
        public Number insertOrder (String orderNotes, String supervisor, String clientUserName){
            String sql = "INSERT INTO Ordered (orderDate, orderNotes, supervisor, client) VALUES (CURDATE(), ?, ?, ?)";
            Connection connection = jdbcTemplate.getDataSource().getConnection();
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(
                    new PreparedStatementCreator() {
                        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                            PreparedStatement ps =
                                    connection.prepareStatement(sql, new String[] {"id"});
                            ps.setString(1, orderNotes);
                            ps.setString(2, supervisor);
                            ps.setString(3, clientUserName);
                            return ps;
                        }
                    },
                    keyHolder);
//            jdbcTemplate.update(sql, orderNotes, supervisor, clientUserName);
            return keyHolder.getKey();

        }

        // Retrieve the last inserted order ID
        public int getLastOrderId () {
            String sql = "SELECT LAST_INSERT_ID() AS orderID";
            return jdbcTemplate.queryForObject(sql, Integer.class);
        }

//        public Ordered getOrderByOrderID(int orderID) {
//            Ordered o = new Ordered();
//
//            String sql = "SELECT * from ordered where orderID = ?";
//            List<Ordered> os ;
//            os = jdbcTemplate.query(sql, new Object[]{orderID}, (rs, rowNum) -> {
//                Ordered o2 = new Ordered();
//                o2.setOrderID(rs.getInt("orderID"));
//                o2.setSupervisor(rs.getString("supervisor"));
//                o2.setClient(rs.getString("client"));
//                o2.se
//                return item;
//            });
//            return jdbcTemplate.
//        }
    }
