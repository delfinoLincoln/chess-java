package boardgame;

public abstract class Piece {
    protected Position position;
    private Board board;

    public Piece() {
    }

    public Piece(Board board) {
        this.board = board;
        position = null;
    }

    protected Board getBoard() {
        return board;
    }

    public abstract boolean[][] possibleMoves();

    public boolean possibleMove(Position position) {
        return possibleMoves()[position.getRow()][position.getColumn()];
    }

    public boolean isThereAnyPossibleMove() {
        boolean[][] matAux = possibleMoves();
        for(int i = 0; i < matAux.length; i++) {
            for(int j = 0; j < matAux.length; j++) {
                if (matAux[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }
}