import java.util.*;

public class Knight extends Piece {
    public Knight(Color c) {  PieceColor = c; }

    public String toString() {
        String out = (PieceColor == Color.WHITE) ? "wn" : "bn";
        return out;
    }

    public List<String> moves(Board b, String loc) {
        ArrayList<String> ValidMoves = new ArrayList<>();
        int[] dc = {-1,-2,-2,-1,1,2,2,1};
        int[] dr = {2,1,-1,-2,-2,-1,1,2};

        for(int i = 0; i < 8; i++){
            String check = shiftLocation(loc, dr[i], dc[i]);
            if(b.onBoard(check) && (b.getPiece(check) == null 
            || b.getPiece(check).color() != this.PieceColor))
            ValidMoves.add(check);
        }
        return ValidMoves;
    }

}