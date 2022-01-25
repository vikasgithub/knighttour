package knighttour;

import java.util.Arrays;
import java.util.concurrent.*;

public class ChessBoard {
    int[][] data = null;
    int rows = 0;
    int cols = 0;

    ChessBoard() {
    }

    ChessBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        data = new int[rows][cols];
    }

    public ChessBoard copy() {
        ChessBoard newChessBoard = new ChessBoard();
        newChessBoard.data = Arrays.stream(this.data).map(int[]::clone).toArray(int[][]::new);
        return newChessBoard;
    }

    public void setMove(int r, int c, int move) {
        this.data[r][c] = move;
    }

    public int getMove(int r, int c) {
        return this.data[r][c];
    }

    public ChessBoard startKnightTour(int x, int y, int P) {
        setMove(x, y, 1);
        KnightTourRecursiveTask knightTourRecursiveTask = new KnightTourRecursiveTask(this, x, y, P, 2);
        System.out.println("Processors: " + Runtime.getRuntime().availableProcessors());
        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        return forkJoinPool.invoke(knightTourRecursiveTask);
    }

    public void displayBoard() {
        for (int i = 0; i < this.data.length; i++) {
            for (int j = 0; j < this.data[0].length; j++) {
                System.out.print(String.format("%4d", this.data[i][j]));
            }
            System.out.println();
        }
    }

    public String toString() {
        return Arrays.deepToString(this.data);
    }

    public boolean isValidMove(int x, int y) {
        return x >= 0 && x < this.data.length && y >= 0 && y < this.data[0].length
                && getMove(x, y) == 0;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}