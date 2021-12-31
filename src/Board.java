import java.awt.*;

public class Board {
    
    private Tile[][] board;
    private int score;
    private int best;

    /**
     * Constructor for a new board
     */
    public Board() {
        board = new Tile[4][4];
        best = 0;
        reset();
    }

    /**
     * Resets the board
     */
    public void reset() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = new Tile(0);
            }
        }
        appear();
        appear();
        score = 0;
    }

    /**
     * Get score
     * @return current score
     */
    public int getScore() {
        return score;
    }

    /**
     * Get best score
     * @return all time best score
     */
    public int getBest() {
        return best;
    }

    /**
     * Draws the board
     * @param g -> graphics to draw
     */
    public void draw(Graphics g) {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                board[row][col].draw(g, 100 * col + 15 * (col + 1), 100 * row + 15 * (row + 1));
            }
        }
    }

    /**
     *  Check if the game is over
     * @return whether the game is over
     */
    public boolean isGameOver() {
        return hasWon() || (!checkHorizontal() && !checkVertical() && !isEmptyTile());
    }

    /**
     * Check if the user has won
     * @return whether the user has won
     */
    public boolean hasWon() {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if (board[row][col].getVal() == 2048) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Make a move
     * @param dir -> direction of the input
     */
    public void move(String dir) {
        switch (dir) {
            case "up":
                moveUp();
                break;
            case "down":
                moveDown();
                break;
            case "right":
                moveRight();
                break;
            case "left":
                moveLeft();
                break;
            default:
                break;
        }
        
        if (score > best) {
            best = score;
        }
    }

    /**
     * Make an up shift
     */
    private void moveUp() {
        boolean moved = false;
        for (int col = 0; col < 4; col++) {
            int top = 0;
            int bot = 1;
            while (bot < 4) {
                if (board[bot][col].getVal() == 0) {
                    bot++;
                } else if (board[top][col].getVal() == 0) {
                    board[top][col] = board[bot][col];
                    board[bot][col] = new Tile(0);
                    moved = true;
                    bot++;
                } else if (board[top][col].equals(board[bot][col])) {
                    score += board[top][col].doubleVal();
                    board[bot][col] = new Tile(0);
                    moved = true;
                    top++;
                    bot++;
                } else if (bot > top + 1) {
                    board[top + 1][col] = board[bot][col];
                    board[bot][col] = new Tile(0);
                    moved = true;
                    top++;
                    bot++;
                } else {
                    top++;
                    bot++;
                }
            }
        }
        if (moved) {
            appear();
        }
    }

    /**
     * Make a down shift
     */
    private void moveDown() {
        boolean moved = false;
        for (int col = 0; col < 4; col++) {
            int top = 2;
            int bot = 3;
            while (top >= 0) {
                if (board[top][col].getVal() == 0) {
                    top--;
                } else if (board[bot][col].getVal() == 0) {
                    board[bot][col] = board[top][col];
                    board[top][col] = new Tile(0);
                    moved = true;
                    top--;
                } else if (board[bot][col].equals(board[top][col])) {
                    score += board[bot][col].doubleVal();
                    board[top][col] = new Tile(0);
                    moved = true;
                    top--;
                    bot--;
                } else if (top < bot - 1) {
                    board[bot - 1][col] = board[top][col];
                    board[top][col] = new Tile(0);
                    moved = true;
                    top--;
                    bot--;
                } else {
                    top--;
                    bot--;
                }
            }
        }
        if (moved) {
            appear();
        }
    }

    /**
     * Make a right shift
     */
    private void moveRight() {
        boolean moved = false;
        for (int row = 0; row < 4; row++) {
            int left = 2;
            int right = 3;
            while (left >= 0) {
                if (board[row][left].getVal() == 0) {
                    left--;
                } else if (board[row][right].getVal() == 0) {
                    board[row][right] = board[row][left];
                    board[row][left] = new Tile(0);
                    moved = true;
                    left--;
                } else if (board[row][right].equals(board[row][left])) {
                    score += board[row][right].doubleVal();
                    board[row][left] = new Tile(0);
                    moved = true;
                    left--;
                    right--;
                } else if (left < right - 1) {
                    board[row][right - 1] = board[row][left];
                    board[row][left] = new Tile(0);
                    moved = true;
                    left--;
                    right--;
                } else {
                    left--;
                    right--;
                }
            }
        }
        if (moved) {
            appear();
        }
    }

    /**
     * Make a left shift
     */
    private void moveLeft() {
        boolean moved = false;
        for (int row = 0; row < 4; row++) {
            int left = 0;
            int right = 1;
            while (right < 4) {
                if (board[row][right].getVal() == 0) {
                    right++;
                } else if (board[row][left].getVal() == 0) {
                    board[row][left] = board[row][right];
                    board[row][right] = new Tile(0);
                    moved = true;
                    right++;
                } else if (board[row][left].equals(board[row][right])) {
                    score += board[row][left].doubleVal();
                    board[row][right] = new Tile(0);
                    moved = true;
                    left++;
                    right++;
                } else if (right > left + 1) {
                    board[row][left + 1] = board[row][right];
                    board[row][right] = new Tile(0);
                    moved = true;
                    left++;
                    right++;
                } else {
                    left++;
                    right++;
                }
            }
        }
        if (moved) {
            appear();
        }
    }
    
    /**
     * Place either a 2 or a 4 tile in a random open position
     */
    private void appear() {
        boolean placed = false;
        while (!placed) {
            int row = (int) (Math.random() * 4);
            int col = (int) (Math.random() * 4);
            if (board[row][col].getVal() == 0) {
                board[row][col] = new Tile();
                placed = true;
            }
        }
    }

    /**
     * Check if there exists an empty tile
     * @return true if there exists an empty tile
     */
    private boolean isEmptyTile() {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if (board[row][col].getVal() == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check if a horizontal move is possible
     * @return boolean indicating whether a horizontal move is possible
     */
    private boolean checkHorizontal() {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col].equals(board[row][col + 1])) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check if a vertical move is possible
     * @return boolean indicating whether a vertical move is possible
     */
    private boolean checkVertical() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 4; col++) {
                if (board[row][col].equals(board[row + 1][col])) {
                    return true;
                }
            }
        }
        return false;
    }
}