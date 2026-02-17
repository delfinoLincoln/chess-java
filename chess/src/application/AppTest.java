package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.exception.ChessException;
import util.ClearScreen;
import util.Spause;
import util.UI;

public class AppTest {
    public static void main(String[] args) {
        ClearScreen.cls();
        Scanner sc = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();

        while (true) {
            try {
                UI.printBoard(chessMatch.getPieces());
                System.out.println();
                System.out.print("Source: ");
                ChessPosition source = UI.readChessPosition(sc);

                boolean[][] possibleMoves = chessMatch.possibleMoves(source);
                ClearScreen.cls();
                UI.printBoard(chessMatch.getPieces(), possibleMoves);

                System.out.println();
                System.out.print("Target: ");
                ChessPosition target = UI.readChessPosition(sc);

                ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
                ClearScreen.cls();
            }
            catch(ChessException e) {
                System.out.println("Error: " + e.getMessage() + " Try again!");
                Spause.waitAnyKey();
                sc.nextLine();
                ClearScreen.cls();
            }
            catch(InputMismatchException e) {
                System.out.println(e.getMessage() + " Try again!");
                Spause.waitAnyKey();
                sc.nextLine();
                ClearScreen.cls();
            }
        }
    }
}