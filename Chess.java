import java.util.*;

import javax.naming.NameAlreadyBoundException;

import java.io.*;

public class Chess {
    public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Usage: java Chess layout moves");
			throw new IllegalArgumentException();
		}
		//reads and validates layout and move files
		ArrayList<String> layout = read_file(args[0]);
		ArrayList<String> moves = read_file(args[1]);

		Piece.registerPiece(new KingFactory());
		Piece.registerPiece(new QueenFactory());
		Piece.registerPiece(new KnightFactory());
		Piece.registerPiece(new BishopFactory());
		Piece.registerPiece(new RookFactory());
		Piece.registerPiece(new PawnFactory());
		Board.theBoard().registerListener(new Logger());

		if(validateLayout(layout) && (validateMoves(moves))){
			executeLayout(layout);
			executeMoves(moves);
		}else{
			throw new IllegalArgumentException("Layout file and or move file invalid");
		}

		System.out.println("Final board:");
		Board.theBoard().iterate(new BoardPrinter());
    }

	/*input: String file name
	output: Returns an arraylist of the file lines without '#' comments*/
	public static ArrayList<String> read_file(String file_name){
		Scanner s = null;
		try{
			s = new Scanner(new File(file_name));
			ArrayList<String> words = new ArrayList<String>();
			while(s.hasNextLine()){
				String word = s.nextLine();
				if(!word.substring(0,1).equals("#")) System.out.println(word);
				if(!word.substring(0,1).equals("#")) words.add(word);
			}
			return words;
		}catch(FileNotFoundException e){
			throw new RuntimeException("File not found");
		}finally{
			if (s != null) s.close();
		}
	}

	/*input: Iterable i
	output: Checks if all layout calls are valid*/
	public static boolean validateLayout(Iterable<String> i){
		HashSet<String> added_pieces = new HashSet<>();
		for(String s : i){
			//Layout format : xn=cp
			if(!(
				(s.length() == (5)) //checks if length is 5
				&& ('a' <= s.charAt(0) &&  s.charAt(0) <= 'h') //checks column 'x' is between a-h
		 		&& ('1' <= s.charAt(1) &&  s.charAt(1) <= '8') //checks row 'n' is between 1 and 8
				&& ((s.charAt(2)) == ('=')) //checks if 3rd index is '='
				&& ("bw").contains(s.substring(3,4)) //checks color 'c' is b or w
				&& ("kqnbrp".contains(s.substring(4,5))) //checks if piece 'p' is kind "kqnbrp"
				&& !(added_pieces.contains(s.substring(0,2))) // checks if peice is already at that location
			)) return false;
			added_pieces.add((s.substring(0,2)));
		}
		return true;
	}

	/*input: Iterable i
	output: Checks if all the moves calls are valid*/
	public static boolean validateMoves(Iterable<String> i){
		for(String s : i){
			//Moves format : xn-ym
			if(!(
				((s.stripTrailing()).length() == (5)) //checks if length is 5
				&& ('a' <= s.charAt(0) &&  s.charAt(0) <= 'h') //checks column 'x' is between a-h
				&& ('1' <= s.charAt(1) &&  s.charAt(1) <= '8') //checks row 'n' is between 1 and 8
				&& ((s.charAt(2)) == ('-')) //checks if 3rd index is '='
				&& ('a' <= s.charAt(3) &&  s.charAt(3) <= 'h') //checks column 'y' is between a-h
				&& ('1' <= s.charAt(4) &&  s.charAt(4) <= '8') //checks row 'm' is between 1 and 8
			)){
				return false;
			}
		}
		return true;
	}

	/*input: Iterable i
	output: add pieces to board from layout list i*/
	public static void executeLayout(Iterable<String> layout){
		for(String l : layout){
			Board.theBoard().addPiece((Piece.createPiece(l.substring(3,5))), l.substring(0, 2));
		}
	}

	/*input: Iterable i
	output: Executes the moves in moves list i*/
	public static void executeMoves(Iterable<String> moves){
		for(String m : moves){
			String from = m.substring(0,2);
			String to = m.substring(3,5);

			(Board.theBoard()).movePiece(from, to);
		}
	}


	/*input: Char c
	output: returns string of associated piece*/
	public static String layoutPiece(char c){
		HashMap <String, String> validPieces = new HashMap<>();
		validPieces.put("k", "king");
		validPieces.put("q", "queen");
		validPieces.put("n", "knight");
		validPieces.put("b", "bishop");
		validPieces.put("r", "rook");
		validPieces.put("p", "pawn");
		return(validPieces.get(String.valueOf(c)));
	}	

	//Class that converts chess index to doube array index
	public static class BoardLocation{
		public int c;
		public int r;
		BoardLocation(String layout_location){
			if(layout_location.length() != 2){
				throw new IllegalArgumentException("Wrong Chess Layout");
			}


			c = layout_location.charAt(0) - 97;
			r = '8' - layout_location.charAt(1);
		}
		
		//to String method for debugging
		public String toString(){
			return ("" + (char)(c + 97) + (8 - r) + " : " + "[" + r  + "]" + "[" + c + "]");
		}
	}
}