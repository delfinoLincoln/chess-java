package com.delfino.lincoln.application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.delfino.lincoln.chess.ChessMatch;
import com.delfino.lincoln.chess.ChessPiece;
import com.delfino.lincoln.chess.ChessPosition;
import com.delfino.lincoln.exception.ChessException;
import com.delfino.lincoln.util.ClearScreen;
import com.delfino.lincoln.util.Spause;
import com.delfino.lincoln.util.UI;

public class App {
    public static void main(String[] args) {
        ClearScreen.cls();
        Scanner sc = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();
        List<ChessPiece> captured = new ArrayList<>();

        while (!chessMatch.getCheckMate()) {
            try {
                ClearScreen.cls();
                UI.printMatch(chessMatch, captured);;
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
                
                if (capturedPiece != null) {
                    captured.add(capturedPiece);
                }
                if (chessMatch.getPromoted() != null) {
                    System.out.print("Enter piece for promotion (B/N/R/Q): ");
                    String type = sc.nextLine().toUpperCase();
                    while(!type.equals("B") && type.equals("N") && type.equals("R") && type.equals("Q")) {
                        System.out.print("Invalid value! Enter piece for promotion (B/N/R/Q): ");
                        type = sc.nextLine().toUpperCase();
                    }
                    chessMatch.replacePromotedPiece(type);
                }
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
        ClearScreen.cls();
        UI.printMatch(chessMatch, captured);
    }
}