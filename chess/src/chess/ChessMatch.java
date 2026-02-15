package chess;

import chess.enums.Color;

import boardgame.Board;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
    private Board board;

    public ChessMatch() {
        board = new Board(8, 8);
        initialSetup();
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

    private void initialSetup() {
        // Teste com peças BRANCAS (vão aparecer em BRANCO - ANSI_WHITE)
        // Posicionando reis e torres brancas
        placeNewPiece('e', 1, new King(board, Color.WHITE)); // Rei branco em e1

        placeNewPiece('a', 1, new Rook(board, Color.WHITE)); // Torres brancas
        placeNewPiece('h', 1, new Rook(board, Color.WHITE));

        // Algumas torres extras para testar
        placeNewPiece('c', 1, new Rook(board, Color.WHITE)); // Torre extra em c1
        placeNewPiece('f', 1, new Rook(board, Color.WHITE)); // Torre extra em f1

        // Teste com peças PRETAS (vão aparecer em AMARELO - ANSI_YELLOW)
        placeNewPiece('e', 8, new King(board, Color.BLACK)); // Rei preto em e8

        placeNewPiece('a', 8, new Rook(board, Color.BLACK)); // Torres pretas
        placeNewPiece('h', 8, new Rook(board, Color.BLACK));

        placeNewPiece('c', 8, new Rook(board, Color.BLACK)); // Torres extras
        placeNewPiece('f', 8, new Rook(board, Color.BLACK));

        // Colocar algumas peças no meio do tabuleiro para testar
        placeNewPiece('d', 4, new Rook(board, Color.WHITE)); // Torre branca no centro
        placeNewPiece('e', 5, new Rook(board, Color.BLACK)); // Torre preta no centro
    }

    private void placeNewPiece(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }
}