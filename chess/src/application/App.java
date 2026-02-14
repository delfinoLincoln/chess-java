package application;

import chess.ChessMatch;
import util.ClearScreen;

public class App {
    public static void main(String[] args) {
        ClearScreen.cls();
        ChessMatch chessMatch = new ChessMatch();
        UI.printBoard(chessMatch.getPieces());
    }
}