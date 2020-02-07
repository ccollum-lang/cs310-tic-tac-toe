package edu.jsu.mcis;

import com.oracle.webservices.internal.api.EnvelopeStyle;

public class TicTacToeModel {
    
    private Mark[][] board; /* Game board */
    private boolean xTurn;  /* True if X is current player */
    private int width;      /* width of game board */
    
    /* ENUM TYPE DEFINITIONS */
    
    /* Mark (represents X, O, or an empty square */
    
    public enum Mark {
        
        X("X"), 
        O("O"), 
        EMPTY("-");

        private String message;
        
        Mark(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    }
    
    /* Result (represents the final state of the game: X wins, O wins, a TIE,
       or NONE if the game is not yet over) */
    
    public enum Result {
        
        X("X"), 
        O("O"), 
        TIE("TIE"), 
        NONE("NONE");

        private String message;
        
        Result(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    }

    /* CONSTRUCTOR */
    
    public TicTacToeModel() {
        
        this(TicTacToe.DEFAULT_WIDTH);
        
    }
    
    /* CONSTRUCTOR */
    
    public TicTacToeModel(int width) {
        
        /* Initialize width; X goes first */
        
        this.width = width;
        xTurn = true;
        
        /* Create board (width x width) as a 2D Mark array */
        
        board = new Mark[width][width];

        /* Initialize board by filling every square with empty marks */
        
        // INSERT YOUR CODE HERE
        for (int row = 0; row < width; row++) {
            for (int col = 0; col < width; col++) {
                board[row][col] = Mark.EMPTY;
            }
        }

    }
	
    public boolean makeMark(int row, int col) {
        
        /* Use "isValidSquare()" to check if the specified location is in range,
           and use "isSquareMarked()" to see if the square is empty!  If the
           specified location is valid, make a mark for the current player, then
           toggle "xTurn" from true to false (or vice-versa) to switch to the
           other player before returning TRUE.  Otherwise, return FALSE. */
        
        // INSERT YOUR CODE HERE
        boolean isMarkMade = false;

        if (isValidSquare(row, col) && !isSquareMarked(row, col)) {
            if(xTurn) {
                board[row][col] = Mark.X;
                xTurn = false;
            }
            else {
                board[row][col] = Mark.O;
                xTurn = true;
            }
            isMarkMade = true;
        }
        return isMarkMade;
        
    }
	
    private boolean isValidSquare(int row, int col) {
        
        /* Return TRUE if the specified location is within the bounds of the board */
        
        // INSERT YOUR CODE HERE
        boolean inBounds = false;
        if (row < width && col < width){
            inBounds = true;
        }
        return inBounds;
        
    }
	
    private boolean isSquareMarked(int row, int col) {
        
        /* Return TRUE if the square at specified location is marked */
        
        // INSERT YOUR CODE HERE
        boolean isMarked = false;
        if (board[row][col] != Mark.EMPTY)
            isMarked = true;

        return isMarked; // remove this line later!
            
    }
	
    public Mark getMark(int row, int col) {
        
        /* Return the mark from the square at the specified location */
        
        // INSERT YOUR CODE HERE
        return board[row][col];
            
    }

    public Result getResult() {
        
        /* Call "isMarkWin()" to see if X or O is the winner, if the game is a
           TIE, or if the game is not over.  Return the corresponding Result
           value */

        // SUPPLY YOUR CODE HERE
        if (isMarkWin(Mark.X))
            return Result.X;

        else if (isMarkWin(Mark.O))
            return Result.O;

        else if (isTie())
            return Result.TIE;

        else
            return Result.NONE;

    }
	
    private boolean isMarkWin(Mark mark) {
        
        /* Check the squares of the board to see if the specified mark is the
           winner */
        
        // INSERT YOUR CODE HERE
        boolean isWinner = false;

        int xHorizSquares = 0;
        int xVertSquares = 0;
        int xDiagOneSquares = 0;
        int xDiagTwoSquares = 0;

        for (int row = 0; row < width; row++) {
            xHorizSquares = 0;
            xVertSquares = 0;

            for (int col = 0; col < width; col++) {
                if (board[row][col] == mark)
                    xHorizSquares++;

                if (board[col][row] == mark)
                    xVertSquares++;

                if (xHorizSquares == width || xVertSquares == width )
                    isWinner = true;
            }
            
            if (board[row][row] == mark)
                xDiagOneSquares++;
            
            if (board[row][width-row-1] == mark)
                xDiagTwoSquares++;

            if (xDiagOneSquares == width || xDiagTwoSquares == width)
                isWinner = true;
        }
        return isWinner; 

    }

    private boolean isTie() {

        /* Check the squares of the board to see if the game is a tie */

        // SUPPLY YOUR CODE HERE
        boolean gameTie = false;

        if (!isMarkWin(Mark.X) && !isMarkWin(Mark.O) && isGameOver())
            gameTie = true;

        return gameTie;

    }

    public boolean isGameOver() {

        /* Return TRUE if the game is over */

        // SUPPLY YOUR CODE HERE
        boolean gameEnd = true;

        for (int row = 0; row < width; row++) {
            for (int col = 0; col < width; col++){
                if (board[row][col] == Mark.EMPTY)
                    gameEnd = false;
            }
        }

        if(isMarkWin(Mark.X) || isMarkWin(Mark.O)){
            gameEnd = true;
        }
        return gameEnd;

    }

    public boolean isXTurn() {
        
        /* Getter for xTurn */
        
        return xTurn;
        
    }

    public int getWidth() {
        
        /* Getter for width */
        
        return width;
        
    }
    
    @Override
    public String toString() {
        
        StringBuilder output = new StringBuilder("  ");
        
        /* Output the board contents as a string (see examples) */
        
        // INSERT YOUR CODE HERE

        for (int i = 0; i < width; i++) {
            output.append(i);
        }
        output.append("\n");

        for (int i = 0; i < width; i++) {
            output.append(i + " ");
            for (int k = 0; k < width; k++) {
                output.append(board[i][k]);
            }
            output.append("\n");
        }
        output.append("\n");


        return output.toString();
        
    }
    
}
