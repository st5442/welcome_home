package Project3.com.welcome_home.repositories;

import Project3.com.welcome_home.commons.DeliveryStatus;
import Project3.com.welcome_home.entities.Delivered;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public class DeliveredRepositoryJDBC {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void changeStatusOfDelivery(Integer orderID, DeliveryStatus deliveryStatus) {
        LocalDate date = LocalDate.now();
        java.sql.Date sqlDate = java.sql.Date.valueOf(date);
        String sql = """
                UPDATE Delivered SET status=?, date=? WHERE orderID=?;
                """;
        jdbcTemplate.update(sql, deliveryStatus.name(), sqlDate, orderID);
    }

}
