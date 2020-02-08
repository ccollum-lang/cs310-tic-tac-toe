package edu.jsu.mcis;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class TicTacToeController implements ActionListener {

    private final TicTacToeModel model;
    private final TicTacToeView view;
    
    /* CONSTRUCTOR */

    public TicTacToeController(int width) {
        
        /* Initialize model, view, and width */

        model = new TicTacToeModel(width);
        view = new TicTacToeView(this, width);
        
    }

    public String getMarkAsString(int row, int col) {
        return (model.getMark(row, col).toString());
    }

    public TicTacToeView getView() {
        return view;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        /* This is the event handler for button clicks. */

        // SUPPLY YOUR CODE FOR THE EMPTY SECTIONS AS COMMENTED BELOW

        /* First, acquire a reference to the clicked button and get its name */

        String name = ((JButton) event.getSource()).getName();
        ArrayList<String> temp;

        /* Parse the row and column from the name (see Line 30 of the View) */

        // SUPPLY YOUR CODE HERE
        //board[row][col].setName("Square" + row + col);

        //temp = new ArrayList(Arrays.asList(name.substring(6).split(",")));

        /* Make a mark at the specified row and column */

        // SUPPLY YOUR CODE HERE
        int row = Integer.parseInt(name.substring(6,7));
        int col = Integer.parseInt(name.substring(7));
        model.makeMark(row, col);
        /* Update the view to show the new mark */

        view.updateSquares();

        /* Get new result */

        TicTacToeModel.Result result = model.getResult();

        /* If the game is over, disable the squares and show the result */

        if (result != TicTacToeModel.Result.NONE) {

            view.disableSquares();
            view.showResult(result.toString());

        }

        /* Otherwise, leave the result field empty */

        else {

            view.clearResult();

        }

    }
}


