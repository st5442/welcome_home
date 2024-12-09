package Project3.com.welcome_home.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class VolunteerRankService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getVolunteerRankings(String startDate, String endDate) {
        String sql = "SELECT d.userName, COUNT(d.orderID) AS taskCount, " +
                "RANK() OVER (ORDER BY COUNT(d.orderID) DESC) AS `rank` " +
                "FROM Delivered d " +
                "WHERE d.date BETWEEN ? AND ? " +
                "GROUP BY d.userName " +
                "ORDER BY `rank`";

        return jdbcTemplate.queryForList(sql, startDate, endDate);
    }
}