package Project3.com.welcome_home.services;

import Project3.com.welcome_home.entities.PieceIn;
import Project3.com.welcome_home.repositories.PieceInRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PieceInService {

    private final PieceInRepository pieceInRepository;

    public PieceInService(PieceInRepository pieceInRepository) {
        this.pieceInRepository = pieceInRepository;
    }

    public List<PieceIn> getAllPiecesIn() {
        return pieceInRepository.findAll();
    }

    public PieceIn savePieceIn(PieceIn pieceIn) {
        return pieceInRepository.save(pieceIn);
    }

    public PieceIn getPieceIn(Integer pieceNum, Integer orderId) {
        return pieceInRepository.findById(new PieceInId(pieceNum, orderId)).orElse(null);
    }
}
