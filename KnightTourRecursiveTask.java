package knighttour;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

class KnightTourRecursiveTask extends RecursiveTask<ChessBoard> {
    private ChessBoard chessBoard;
    private int nextMove;
    int x;
    int y;
    int maxMoves;
    static int[] horizontalMoves = new int[]{-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] verticalMoves = new int[]{1, 2, 2, 1, -1, -2, -2, -1};

    public KnightTourRecursiveTask(ChessBoard chessBoard, int x, int y, int maxMoves, int nextMove) {
        this.chessBoard = chessBoard;
        this.x = x;
        this.y = y;
        this.maxMoves = maxMoves;
        this.nextMove = nextMove;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getMaxMoves() {
        return maxMoves;
    }

    @Override
    protected ChessBoard compute() {
        if (nextMove == getMaxMoves() + 1) {
            return chessBoard;
        }
        List<KnightTourRecursiveTask> subtasks = new ArrayList<>();
        for (int moveNo = 0; moveNo < 8; moveNo++) {
            int nextX = x + horizontalMoves[moveNo];
            int nextY = y + verticalMoves[moveNo];
            if (chessBoard.isValidMove(nextX, nextY)) {
                ChessBoard newChessboard = chessBoard.copy();
                newChessboard.setMove(nextX, nextY, nextMove);
                KnightTourRecursiveTask knightTourRecursiveTask = new KnightTourRecursiveTask(newChessboard, nextX, nextY,
                        maxMoves, nextMove + 1);
                subtasks.add(knightTourRecursiveTask);
            }
        }
        if (subtasks.size() == 0) {
            return null;
        }
        for(KnightTourRecursiveTask subTask : subtasks) {
            subTask.fork();
        }
        for(KnightTourRecursiveTask subTask : subtasks) {
            ChessBoard cb = subTask.join();
            if (cb != null) {
                return cb;
            }
        }

        return chessBoard;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public int getNextMove() {
        return nextMove;
    }
}