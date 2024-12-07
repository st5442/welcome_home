package Project3.com.welcome_home.repositories;

import Project3.com.welcome_home.entities.Location;
import Project3.com.welcome_home.entities.LocationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, LocationId> {
    @Query("select p.itemID, p.pieceNum, p.pDescription, l.roomNum, l.shelfNum, l.shelfDescription from Piece p join Location l on p.roomNum = l.roomNum where p.itemID=:itemId")
    Optional<List<Object>> findLocationsByItemId(@Param("itemId") Integer itemId);

}
