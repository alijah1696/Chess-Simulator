# Chess-Simulator
Chess Simulator In Java

# Chess Simulator

A Java-based Chess Simulator implementing core chess mechanics with an emphasis on object-oriented design principles. This project serves as a foundational framework for simulating chess games, facilitating further development and experimentation.

## Features

- Comprehensive representation of chess pieces: King, Queen, Bishop, Knight, Rook, and Pawn.
- Factory pattern implementation for dynamic piece creation.
- Board management with support for listeners and iterators.
- Custom exception handling for robust error management.
- Console-based board visualization.

## Project Structure

### Pieces

Each chess piece is represented by a class extending the abstract `Piece` class, encapsulating its unique movement logic.

- `Piece.java`: Abstract base class defining common attributes and methods for all pieces.
- `King.java`, `Queen.java`, `Bishop.java`, `Knight.java`, `Rook.java`, `Pawn.java`: Concrete implementations specifying movement rules.
- `KingFactory.java`, `QueenFactory.java`, `BishopFactory.java`, `KnightFactory.java`, `RookFactory.java`, `PawnFactory.java`: Factory classes for creating instances of each piece type.

### Board Management

Handles the state and behavior of the chessboard.

- `Board.java`: Manages piece placement, movement, and board state.
- `BoardInternalIterator.java`: Provides iteration over board positions.
- `BoardListener.java`: Defines an interface for responding to board events.
- `BoardPrinter.java`: Handles console-based visualization of the board.

### Utilities

Supporting classes for game functionality.

- `Color.java`: Enumeration defining piece colors (e.g., WHITE, BLACK).
- `Logger.java`: Utility for logging game events and actions.

### Exceptions

Custom exceptions for error handling.

- `InvalidColor.java`: Thrown when an invalid color is specified.
- `InvalidFile.java`: Thrown when an invalid file is accessed.
- `InvalidLocation.java`: Thrown when an invalid board location is referenced.
- `InvalidMove.java`: Thrown when an illegal move is attempted.
- `PieceNotRegistered.java`: Thrown when a piece factory is not registered.

### Main Application

- `Chess.java`: Entry point for the application, initializing the board and managing gameplay.

## Key Functionalities

### Piece Movement

Each piece class implements the `isValidMove(Position from, Position to)` method, determining the legality of a move based on chess rules.

### Board Operations

- `placePiece(Piece piece, Position position)`: Places a piece on the board at the specified position.
- `movePiece(Position from, Position to)`: Moves a piece from one position to another, validating the move.
- `getPiece(Position position)`: Retrieves the piece at a given position.
- `removePiece(Position position)`: Removes the piece from the specified position.

### Board Visualization

- `printBoard()`: Outputs the current state of the board to the console, displaying piece positions.

### Event Handling

Implementing `BoardListener` allows for custom responses to board events, such as piece movement or capture.
