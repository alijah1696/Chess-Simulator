import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;

public class Test {

  // Run "java -ea Test" to run with assertions enabled (If you run
  // with assertions disabled, the default, then assert statements
  // will not execute!)

  public static void test1() {
    Board b = Board.theBoard();
    Piece.registerPiece(new PawnFactory());
    Piece p = Piece.createPiece("bp");
    b.addPiece(p, "a3");
    assert b.getPiece("a3") == p;

    //piece at a3 on board should be a black pawn
  }

  public static void test2() {
    Board b = Board.theBoard();
    Piece.registerPiece(new RookFactory());
    Piece p = Piece.createPiece("wr");
    b.addPiece(p, "h8");
    assert p.moves(b, "h8").contains("h1");

    /*White Rook at h-8 should be able to
    move to h-1 which is across the board vertically
     with no piece in its way*/
  }

  public static void test3() {
    Board b = Board.theBoard();
    Piece.registerPiece(new PawnFactory());
    Piece p = Piece.createPiece("bp");
    b.addPiece(p, "a8");
    b.addPiece(p, "a7");
    assert !p.moves(b, "a8").contains("a7");

    /*Black pawn at a-8 shouldnt be able to
    move to a-7 b/c another pawn is in the way */
  }
 
  public static void main(String[] args) {
    Board b = Board.theBoard();
    Piece.registerPiece(new PawnFactory());
    Piece.registerPiece(new RookFactory());
    Piece.registerPiece(new BishopFactory());
    Piece.registerPiece(new QueenFactory());
    Piece.registerPiece(new KingFactory());
    Piece.registerPiece(new KnightFactory());

    Piece wp = Piece.createPiece("wp");
    Piece bp = Piece.createPiece("bp");

    Piece wr = Piece.createPiece("wr");
    Piece br = Piece.createPiece("br");
    
    Piece wb = Piece.createPiece("wb");
    Piece bb = Piece.createPiece("bb");

    Piece wn = Piece.createPiece("wn");
    Piece bn = Piece.createPiece("bn");

    Piece wk = Piece.createPiece("wk");
    Piece bk = Piece.createPiece("bk");

    Piece wq = Piece.createPiece("wq");
    Piece bq = Piece.createPiece("bq");

    // test1();
    // test2();
    // test3();
    populateBoard(b);
    b.registerListener(new Logger());
    printBoard(b);
  }

  public static void printBoard(Board b){
    for(int i = -2; i < 8; i++){
      if(i == -2){
        System.out.print("   ");
        for(int z = 0; z < 8; z++){
          System.out.print("  " + (char)(97 + z));
        }
        System.out.print("\n");
        continue;
      }

      if(i == -1){
        System.out.print("    ");
        for(int z = 0; z < 8; z++){
          System.out.print(" "  + "__");
        }
        System.out.println("\n");
        continue;
      }

      for(int j = -1; j < 8; j++){
        if(j == -1){
          System.out.print("" + (8-i) + " |  ");
          continue;
        }

        String check = "" + (char)(97 + j) + (char)('8' - i);
        if(b.getPiece(check) == null) System.out.print("[] ");
        else System.out.print(b.getPiece(check) + " ");
      }
      System.out.println("\n");
    }
  }

  public static void printMoves( String loc, Board b){
    // if(b.getPiece(loc) == null) b.addPiece(p, loc);
    java.util.List<String> vms = (b.getPiece(loc)).moves(b, loc);
    for(int i = -2; i < 8; i++){
      if(i == -2){
        System.out.print("   ");
        for(int z = 0; z < 8; z++){
          System.out.print("  " + (char)(97 + z));
        }
        System.out.print("\n");
        continue;
      }
      if(i == -1){
        System.out.print("    ");
        for(int z = 0; z < 8; z++){
          System.out.print(" "  + "__");
        }
        System.out.println("\n");
        continue;
      }
      for(int j = -1; j < 8; j++){
        if(j == -1){
          System.out.print("" + (8-i) + " |  ");
          continue;
        }

        String check = "" + (char)(97 + j) + (char)('8' - i);
        if(vms.contains(check)){
          if(b.getPiece(check) != null) System.out.print("" + b.getPiece(check).toString().charAt(0) + "# " );
          else System.out.print("## ");
        }
        else if (b.getPiece(check) == null) System.out.print("[] ");
        else System.out.print(b.getPiece(check) + " ");
      }
      System.out.println("\n");
    }
  }

  public static void populateBoard(Board b){
    Piece.registerPiece(new PawnFactory());
    Piece.registerPiece(new RookFactory());
    Piece.registerPiece(new BishopFactory());
    Piece.registerPiece(new QueenFactory());
    Piece.registerPiece(new KingFactory());
    Piece.registerPiece(new KnightFactory());

    Piece wp = Piece.createPiece("wp");
    Piece bp = Piece.createPiece("bp");

    Piece wr = Piece.createPiece("wr");
    Piece br = Piece.createPiece("br");
    
    Piece wb = Piece.createPiece("wb");
    Piece bb = Piece.createPiece("bb");

    Piece wn = Piece.createPiece("wn");
    Piece bn = Piece.createPiece("bn");

    Piece wk = Piece.createPiece("wk");
    Piece bk = Piece.createPiece("bk");

    Piece wq = Piece.createPiece("wq");
    Piece bq = Piece.createPiece("bq");

    Piece[] pieces = {br,bn,bb,bq,bk,bb,bn,br,wr,wn,wb,wq,wk,wb,wn,wr};
    for(int i = 0; i < 16; i++){
      if(i < 8){
        String specialLocation = Piece.shiftLocation("a8", 0, i);
        String pawnLocation = Piece.shiftLocation("a7",0,i);
        b.addPiece(pieces[i], specialLocation);
        b.addPiece(bp, pawnLocation);
      }else{
        String specialLocation = Piece.shiftLocation("a1", 0, (i-8));
        String pawnLocation = Piece.shiftLocation("a2",0,(i-8));
        b.addPiece(pieces[i], specialLocation);
        b.addPiece(wp, pawnLocation);
      }
    }
  }
}

