public class Game {
    public int turn = 1;

    public int[][][] board = new int[3][3][3];


    public void gameReset() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                for (int z = 0; z < 3; z++) {
                    board[x][y][z] = 0;
                }
            }
        }
    }

    public void boardPrint() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                for (int z = 0; z < 3; z++) {
                    System.out.print(board[z][y][x]);
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public int evaluate(int x, int y, int z) {

        int matchCount = 0;
        if (this.board[x][y][z] != 0 && this.board[0][y][z] == this.board[1][y][z] && this.board[1][y][z] == this.board[2][y][z])
            matchCount++;
        if (this.board[x][y][z] != 0 && this.board[x][0][z] == this.board[x][1][z] && this.board[x][1][z] == this.board[x][2][z])
            matchCount++;
        if (this.board[x][y][z] != 0 && this.board[x][y][0] == this.board[x][y][1] && this.board[x][y][1] == this.board[x][y][2])
            matchCount++;

        return matchCount;
    }

    public boolean move(int x1, int y1, int z1, int x2, int y2, int z2) {
        if (isValidMove(x1, y1, z1, x2, y2, z2)) {
            board[x2][y2][z2] = board[x1][y1][z1];
            board[x1][y1][z1] = 0;
            if (gameState() == 0)
                nextTurn();
            return true;
        } else
            return false;
    }

    public boolean setup(int x, int y, int z) {
        if (isValidSetup(x, y, z)) {
            board[x][y][z] = turn;
            if (gameState() == 0)
                nextTurn();
            return true;
        } else
            return false;
    }

    public boolean fly(int x1, int y1, int z1, int x2, int y2, int z2) {
        if (isValidFly(x1, y1, z1, x2, y2, z2)) {
            board[x2][y2][z2] = board[x1][y1][z1];
            board[x1][y1][z1] = 0;


            if (gameState() == 0)
                nextturn();
            return true;
        } else
            return false;
    }

    public int gameState() {
        int RedValidMoves = 0;
        int BlueValidMoves = 0;

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                for (int z = 0; z < 3; z++) {
                    if (hasValidMove(x, y, z)) {
                        if (board[x][y][z] == 1)
                            RedValidMoves++;
                        else if (board[x][y][z] == 2)
                            BlueValidMoves++;
                    }
                }
            }
        }
        if (RedValidMoves != 0 && BlueValidMoves != 0)
            return 0;
        if (RedValidMoves == 0 && BlueValidMoves != 0)
            return 1;
        if (BlueValidMoves == 0 && RedValidMoves != 0)
            return 2;

        return 3;

    }

}
