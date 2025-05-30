import java.util.*;

import javax.management.RuntimeErrorException;

public class Pawn extends Piece {
    public Pawn(Color c) { 
        PieceColor = c; 
    }

    public String toString() {
        String out = (PieceColor == Color.WHITE) ? "wp" : "bp";
        return out;
    }

    public List<String> moves(Board b, String loc) {
        ArrayList<String> validMoves = new ArrayList<>();
        int direction = (this.PieceColor == Color.WHITE) ? 1 : -1;
        int ExtraMoveRow = (this.PieceColor == Color.WHITE) ? 6 : 1;

        if(b.onBoard(loc)){
            String infront = shiftLocation(loc, direction, 0);
            if(b.onBoard(infront) && b.getPiece(infront) == null){//checks if can move infront
                validMoves.add(infront);
                String extraMove = shiftLocation(infront, direction, 0);
                if((b.onBoard(extraMove) && b.getPiece(extraMove) == null) && ((new Chess.BoardLocation(loc).r) == ExtraMoveRow)) validMoves.add(extraMove);
                //checks if in starting row and second position infront is free
    
            }
            for(int i = -1; i <= 1; i+=2){ //checks if can capture left and right diagonal
                String diagonal = shiftLocation(infront, 0, i);
                if(b.onBoard(diagonal) && (b.getPiece(diagonal) != null 
                && (b.getPiece(diagonal).color() != this.PieceColor)))
                validMoves.add(diagonal);
            }
        }
        return validMoves;
    }

    

}