package Project3.com.welcome_home.repositories;

import Project3.com.welcome_home.entities.Piece;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PieceRepository extends JpaRepository<Piece, Integer> {
}
