package knighttour;

public class Main {
    public static void main(String[] args) {
        ChessBoard cb = new ChessBoard(20, 20);
        ChessBoard solution = cb.startKnightTour(0, 0, 200);
        solution.displayBoard();
    }
}
