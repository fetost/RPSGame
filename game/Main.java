package game;

import java.io.IOException;
import java.util.Scanner;

import javax.swing.JDialog;


/**
 * Entry point for the Rock-Paper-Scissors game.
 * <p>
 * Handles user input, sets up the network connection, the GUI, 
 * and runs the main game loop.
 * </p>
 */

public class Main {

    /**
     * Starts the Rock-Paper-Scissors game.
     * <p>
     * Prompts the user to host or join a game, establishes
     * a connection, and repeatedly plays rounds until
     * the program is terminated.
     * </p>
     *
     * @param args command-line arguments (not used)
     * @throws ValidationException 
     * @throws IOException if a network error occurs
     */

    public static void main(String[] args) throws ValidationException, IOException {       
        boolean choice = GameDesign.isHosting(); // have to make static reference.
        String userName = GameDesign.setUsername().trim();
        String host = null;
        if (!choice) {
            host = GameDesign.isNotHosting().trim();
        }

        ConnectionManager cm = new ConnectionManager(choice, userName, host);
        cm.setUpConnection();
        String[] moves = {"rock", "paper", "scissors"};

        while (true) {
           int move = GameDesign.gameScreen();
           if (move == -1 || move == 3) {
                cm.sendMessage("quit");
                cm.closeSocket();
                break;
           }
           String userMove = moves[move];
           cm.sendMessage(userMove);

           JDialog waitingScreen = GameDesign.showGeneralWaitingScreen();
           String opponentMove = cm.receiveMessage();
           if (opponentMove.equalsIgnoreCase("quit")) {
                waitingScreen.dispose();
                GameDesign.ifOpponentQuit();
                cm.closeSocket();
                break;
            }
            waitingScreen.dispose();

            /* 
            Creating an instance (object) of the TypeOfPlay class.
            The constructor (TypeOfPlay) is called and gets the data.
            Now the "play" variable knows what the moves are, and can call determineWin() using the stored data. 
            */
            String opponentUsername = cm.getOpponentUsername();
            TypeOfPlay play = new TypeOfPlay(userMove, opponentMove); 
            boolean win = play.determineWin();

            if (userMove.equals(opponentMove)) {
                GameDesign.isDraw(userMove, opponentMove, userName, opponentUsername);
            }
            else if (win) {
                GameDesign.isWin(userMove, opponentMove, userName, opponentUsername);
            }
            else {
                GameDesign.isLoss(userMove, opponentMove, userName, opponentUsername);
            }
        }
    }
}
