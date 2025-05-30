import java.util.*;

public class Queen extends Piece {
    public Queen(Color c) {  PieceColor = c; }

    public String toString() {
        String out = (PieceColor == Color.WHITE) ? "wq" : "bq";
        return out;
    }

    public List<String> moves(Board b, String loc) {
        List<String> ValidMoves = moveDiagonal(b, loc);
        ValidMoves.addAll(moveStraight(b, loc));
        return ValidMoves;
    }
}