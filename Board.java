import java.util.ArrayList;
import java.util.HashSet;

import javax.naming.ldap.HasControls;

public class Board {

    private Piece[][] pieces = new Piece[8][8];
    private static Board CurrentBoard;
    private HashSet<BoardListener> BoardListeners = new HashSet<>();

    private Board(){ }
    
    /*Input: none
      Output: returns private board variable*/
    public static Board theBoard() {
	    if(CurrentBoard == null) CurrentBoard = new Board();
        return CurrentBoard;
    }

    /*Input: String of chess location
      Output: returns piece at location or null if empty
              will throw error if location is not board*/
    public Piece getPiece(String loc) {
        if(!onBoard(loc)){
            throw new RuntimeException(loc + " is not on the board");
        }
        ArrayList<String> validMoves = new ArrayList<>();
        Chess.BoardLocation l = new Chess.BoardLocation(loc);
        int r = l.r;
        int c = l.c;
        return pieces[r][c];
    }

    /*Input: Piece p and String loc
      Output: adds Piece p to Chess location.
              throw error if occupied*/
    public void addPiece(Piece p, String loc) {
        if(!onBoard(loc)){
            throw new IllegalArgumentException(loc + " is not located on the board");
        }

        Chess.BoardLocation l = new Chess.BoardLocation(loc);
        if(pieces[l.r][l.c] == null) pieces[l.r][l.c] = p;
        else{
            throw new RuntimeException(pieces[l.r][l.c] + " is already at " + loc);
        }
    }

    /*Input: String of Chess Location
      Output: returns if chess location is valid*/
    public Boolean onBoard(String loc){
        if(loc.length() != 2){
            throw new IllegalArgumentException("Wrong Chess layout foramt");
        }

        Chess.BoardLocation l = new Chess.BoardLocation(loc);
        int r = (new Chess.BoardLocation(loc)).r;
        int c = (new Chess.BoardLocation(loc)).c;
        if(0 <= r && r < 8 && 0 <= c && c < 8){
            return true;
        }
        return false;
    }


    public void movePiece(String from, String to) {
        if(onBoard(from) && (getPiece(from) != null) && onBoard(to)){
            if(getPiece(from).moves(CurrentBoard, from).contains(to)){
                updateMove(from, to, getPiece(from));
                if(getPiece(to) != null){
                    updateCapture(getPiece(from), getPiece(to));
                }
                Chess.BoardLocation f = new Chess.BoardLocation(from);
                Chess.BoardLocation t = new Chess.BoardLocation(to);

                pieces[t.r][t.c] = pieces[f.r][f.c];
                pieces[f.r][f.c] = null;
                return;
            }
            throw new RuntimeException("Cannot move " + getPiece(from) + " from " + from + " to " + to + ".");
        }
        throw new RuntimeException("Move request from " + from + " to " + to + " invalid");
    }

    public void clear() {
        pieces = new Piece[8][8];
    }

    public void registerListener(BoardListener bl) {
        BoardListeners.add(bl);
    }

    public void removeListener(BoardListener bl) {
        BoardListeners.remove(bl);
    }

    public void removeAllListeners() {
        BoardListeners.clear();
    }

    public void iterate(BoardInternalIterator bi) {
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                String loc = Piece.shiftLocation("a8", -i, j);
                bi.visit(loc, getPiece(loc));
            }
        }
    }

    private void updateMove(String from, String to, Piece p){
        for(BoardListener bl : BoardListeners){
            bl.onMove(from, to ,p);
        }
    }

    private void updateCapture(Piece attacker, Piece captured){
        for(BoardListener bl : BoardListeners){
            bl.onCapture(attacker, captured);
        }
    }
}