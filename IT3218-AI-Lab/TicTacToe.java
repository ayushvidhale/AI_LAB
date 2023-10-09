import java.util.Arrays;

public class TicTacToe {
    private char[][] board;
    private int xCount;
    private int oCount;
    private int emptyCount;

    public TicTacToe() {
        board = new char[3][3];
        initializeBoard();
    }

    private void initializeBoard() {
        for (char[] row : board) {
            Arrays.fill(row, ' ');
        }
    }

    public void printBoard() {
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + " | ");
            }
            System.out.println();
            System.out.println("--------------");
        }
    }

    public void printBoardConfiguration() {
        for (char[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }

    public void printPossibleMovesMatrix() {
        int[][] possibleMoves = new int[3][3];
        int moveCount = 1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    possibleMoves[i][j] = moveCount++;
                } else {
                    possibleMoves[i][j] = 0;
                }
            }
        }

        for (int[] row : possibleMoves) {
            System.out.println(Arrays.toString(row));
        }
    }

    public void makeMove(int row, int col, char player) {
        if (isValidMove(row, col)) {
            board[row][col] = player;
            updateCounts(player);
        } else {
            System.out.println("Invalid move! Please try again.");
        }
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
    }

    private void updateCounts(char player) {
        if (player == 'X') {
            xCount++;
        } else if (player == 'O') {
            oCount++;
        }
        emptyCount = 9 - (xCount + oCount);
    }

    public void play() {
        makeMove(0, 0, 'X');
        makeMove(0, 1, 'O');
        makeMove(1, 1, 'X');
        makeMove(1, 0, 'O');
        makeMove(2, 2, 'X');
        makeMove(2, 0, 'O');
        makeMove(0, 2, 'X');
        makeMove(1, 2, 'O');
        makeMove(2, 1, 'X');
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.printBoard();
        System.out.println("Number of X's: " + game.xCount);
        System.out.println("Number of O's: " + game.oCount);
        System.out.println("Number of empty spaces: " + game.emptyCount);
        System.out.println("Board Configuration:");
        game.printBoardConfiguration();
        System.out.println("Possible Moves Matrix:");
        game.printPossibleMovesMatrix();
        System.out.println("Playing the game...");
        game.play();
        System.out.println("Updated Board:");
        game.printBoard();
    }
}