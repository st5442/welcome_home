package Project3.com.welcome_home.services;

import Project3.com.welcome_home.entities.Piece;
import Project3.com.welcome_home.repositories.PieceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PieceService {

    private final PieceRepository pieceRepository;

    public PieceService(PieceRepository pieceRepository) {
        this.pieceRepository = pieceRepository;
    }

    public List<Piece> getAllPieces() {
        return pieceRepository.findAll();
    }

    public Piece savePiece(Piece piece) {
        return pieceRepository.save(piece);
    }

    public Piece getPiece(Integer itemId, Integer pieceNum) {
        return pieceRepository.findById(new PieceId(itemId, pieceNum)).orElse(null);
    }
}
