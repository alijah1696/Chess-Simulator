import java.util.*;

public class Bishop extends Piece {
    public Bishop(Color c) { PieceColor = c;}

    public String toString() {
        String out = (PieceColor == Color.WHITE) ? "wb" : "bb";
        return out;
    }

    public List<String> moves(Board b, String loc) {
        return moveDiagonal(b, loc);
    }
}