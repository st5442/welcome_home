package Project3.com.welcome_home.repositories;

import Project3.com.welcome_home.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    @Query("select i.ItemID from Item i where i.ItemID=:itemid")
    Optional<Item> findItemByItemID(@Param("itemid") Integer itemid);
}
