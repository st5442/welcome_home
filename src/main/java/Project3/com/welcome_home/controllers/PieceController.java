package Project3.com.welcome_home.controllers;

import Project3.com.welcome_home.entities.Piece;
import Project3.com.welcome_home.services.PieceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pieces")
public class PieceController {

    private final PieceService pieceService;

    public PieceController(PieceService pieceService) {
        this.pieceService = pieceService;
    }

    @GetMapping
    public List<Piece> getAllPieces() {
        return pieceService.getAllPieces();
    }

    @PostMapping
    public Piece createPiece(@RequestBody Piece piece) {
        return pieceService.savePiece(piece);
    }

    @GetMapping("/{itemId}/{pieceNum}")
    public Piece getPiece(@PathVariable Integer itemId, @PathVariable Integer pieceNum) {
        return pieceService.getPiece(itemId, pieceNum);
    }
}
