package chess;

import chess.enums.Color;
import chess.exception.ChessException;
import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
    private int turn;
    private Color curentPlayer;
    private Board board;

    public ChessMatch() {
        board = new Board(8, 8);
        turn = 1;
        curentPlayer = Color.WHITE;
        initialSetup();
    }

    public int getTurn() {
        return turn;
    }

    public Color getCurrentPlayer() {
        return curentPlayer;
    }

    public ChessPiece[][] getPieces() {
        ChessPiece[][] matPiece = new ChessPiece[board.getRows()][board.getColumns()];
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                matPiece[i][j] = (ChessPiece) board.piece(i, j);
            }
        }
        return matPiece;
    }

    public boolean[][] possibleMoves(ChessPosition source) {
        Position position = source.toPosition();
        validateSourcePosition(position);
        return board.piece(position).possibleMoves();
    }

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        validateTargetPosition(source, target);
        Piece capturedPiece = makeMove(source, target);
        nextTurn();
        return (ChessPiece) capturedPiece;
    }

    private Piece makeMove(Position source, Position target) {
        Piece piece = board.removePiece(source);
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(piece, target);
        return capturedPiece;
    }

    private void validateSourcePosition(Position position) {
        if(!board.thereIsAPiece(position)) {
            throw new ChessException("There is no piece on source position!");
        }
        if (curentPlayer != ((ChessPiece)board.piece(position)).getColor()) {
            throw new ChessException("The chosen piece it's not yours!");
        }
        if (!board.piece(position).isThereAnyPossibleMove()) {
            throw new ChessException("There is no possible moves for the chosen piece!");
        }
    }

    private void validateTargetPosition(Position source, Position target) {
		if (!board.piece(source).possibleMove(target)) {
			throw new ChessException("The chosen piece can't move to target position!");
		}
	}

    private void nextTurn() {
        turn ++;
        curentPlayer = (curentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private void placeNewPiece(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }

    private void initialSetup() {
        // peças brancas
        placeNewPiece('e', 1, new King(board, Color.WHITE));

        placeNewPiece('a', 1, new Rook(board, Color.WHITE));
        placeNewPiece('h', 1, new Rook(board, Color.WHITE));

        placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('f', 1, new Rook(board, Color.WHITE));

        // peças amarelas(substuindo as pretas)
        placeNewPiece('e', 8, new King(board, Color.BLACK));

        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
        placeNewPiece('h', 8, new Rook(board, Color.BLACK));

        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('f', 8, new Rook(board, Color.BLACK));

        placeNewPiece('d', 4, new Rook(board, Color.WHITE));
        placeNewPiece('e', 5, new Rook(board, Color.BLACK));
    }
}