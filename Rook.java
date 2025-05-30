import java.util.*;

public class Rook extends Piece {
    public Rook(Color c) { PieceColor = c; }

    public String toString() {
        String out = (PieceColor == Color.WHITE) ? "wr" : "br";
        return out;
    }

    public List<String> moves(Board b, String loc) {
        return moveStraight(b, loc);
    }

}