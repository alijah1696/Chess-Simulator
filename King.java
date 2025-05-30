import java.util.*;
import java.lang.Math;

public class King extends Piece {
    public King(Color c) {   PieceColor = c; }

    public String toString() {
        String out = (PieceColor == Color.WHITE) ? "wk" : "bk";
        return out;
    }

    public List<String> moves(Board b, String loc) {
        ArrayList<String> AllMoves =  moveStraight(b, loc);
        AllMoves.addAll(moveDiagonal(b, loc));
        ArrayList<String> ValidMoves = new ArrayList<>();
        for(String s : AllMoves){
            int rdiff = s.charAt(1) - loc.charAt(1);
            int cdiff = s.charAt(0) - loc.charAt(0);

            if(!(Math.abs(rdiff) > 1 || Math.abs(cdiff) > 1)) ValidMoves.add(s);
        }
        return ValidMoves;
    }

}