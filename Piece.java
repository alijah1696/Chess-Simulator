import java.util.*;

abstract public class Piece {
    protected Color PieceColor;
    private static HashMap<Character, PieceFactory> PieceFactories = new HashMap<>();
    
    public static void registerPiece(PieceFactory pf) {
        if(!PieceFactories.containsKey(pf.symbol())) PieceFactories.put(pf.symbol(), pf);
    }

    public static Piece createPiece(String name) {
        if(PieceFactories.containsKey(name.charAt(1))){
            if(!"wb".contains("" + name.charAt(0))) throw new IllegalArgumentException("Invalid piece color: " + name.charAt(0));
            Color c = (name.charAt(0) == 'w') ? Color.WHITE : Color.BLACK;
            return (PieceFactories.get(name.charAt(1))).create(c);
        }else{
            throw new IllegalArgumentException("No piece factory added for piece \'" + (name.charAt(1)) + "\'");
        }
    }

    public Color color() {
        return PieceColor;
    }

    abstract public String toString();

    abstract public List<String> moves(Board b, String loc);

//movemnet methods
    protected ArrayList<String> moveStraight(Board b, String loc) {
        ArrayList<String> validMoves = new ArrayList<>();
        int[] dr = {-1, 0, 1, 0};  // direction of rows
        int[] dc = {0, 1, 0, -1};  // direction of columns
    
        for (int i = 0; i < 4; i++) {
            for (int step = 1; step < 8; step++) {
                String newLoc = shiftLocation(loc, dr[i]*step, dc[i]*step);
                if (!b.onBoard(newLoc)) break;
                if (b.getPiece(newLoc) != null) {
                    if (b.getPiece(newLoc).color() != this.color()) validMoves.add(newLoc);
                    break;
                }
                validMoves.add(newLoc);
            }
        }
            return validMoves;
        }
    
    protected ArrayList<String> moveDiagonal(Board b, String loc) {
        ArrayList<String> validMoves = new ArrayList<>();
        int[] dr = {-1, -1, 1, 1};  // direction of rows
        int[] dc = {-1, 1, -1, 1};  // direction of columns
    
        for (int i = 0; i < 4; i++) {
            for (int step = 1; step < 8; step++) {
                String newLoc = shiftLocation(loc, dr[i]*step, dc[i]*step);
                if (!b.onBoard(newLoc)) break;
                if (b.getPiece(newLoc) != null) {
                if (b.getPiece(newLoc).color() != this.color()) validMoves.add(newLoc);
                    break;
                }
                validMoves.add(newLoc);
                }
            }
            return validMoves;
        }
    
// Helper method to shift a location
    public static String shiftLocation(String loc, int dr, int dc) {
        char row = (char)(loc.charAt(1) + dr);
        char col = (char)(loc.charAt(0) + dc);
        return ("" + col + row);
    }
    
}