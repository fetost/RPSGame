package game;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

// TODO: double check the logic of the server/client connection. 
// TODO: change hardcoded "localhost", allowing another computer to connect. 
// TODO: implement username(?). Maybe add userName in the constructor for ConnectionManager?

/**
 * Entry point for the Rock-Paper-Scissors game.
 * <p>
 * Handles user input, sets up the network connection,
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
     * @throws ValidationException if user input is invalid
     * @throws IOException if a network error occurs
     */

    public static void main(String[] args) throws ValidationException, IOException {       
        Scanner scanner = new Scanner(System.in);
        System.out.println("Are you hosting? (y/n)");
        System.out.print("> ");

        String choice = scanner.nextLine().trim().toLowerCase(); 
        if (!choice.equals("y") && !choice.equals("n")) {
            throw new ValidationException("Learn to read dummy");
        }

        System.out.println("Type you username");
        System.out.print("> ");

        String userName = scanner.nextLine().trim();
        if (userName.isEmpty()) {   
            throw new ValidationException("Invalid username");
        }
        
        // Instances are declared here so its not made each iteration
        Random random = new Random(); 
        ConnectionManager cm = new ConnectionManager(choice, userName);
        cm.setUpConnection();
    
        while (true) {
            System.out.println("----------------------------");
            System.out.println("Enter one of the moves below:");
            System.out.println("Rock" + "\n" + "Paper" + "\n" + "Scissors");
            System.out.print("> ");

            String userMove = scanner.nextLine().trim(); 
            if (userMove.isEmpty()) { // have to use isEmpty because nextLine() is never null
                throw new ValidationException("Type something idiot");
            }

            cm.sendMessage(userMove);
            System.out.println("Waiting for opponent...");
            String opponentMove = cm.receiveMessage();
            String opponentUsername = cm.getOpponentUsername();
            
            System.out.println(userName + " move is: " + userMove);
            System.out.println(opponentUsername + " move is: " + opponentMove);

            /* 
            Creating an instance (object) of the TypeOfPlay class.
            The constructor (TypeOfPlay) is called and gets the data.
            Now the "play" variable knows what the moves are, and can call determineWin() using the stored data. 
            */
            TypeOfPlay play = new TypeOfPlay(userMove, opponentMove); 
            boolean win = play.determineWin();

            if (userMove.equals(opponentMove)) {
                System.out.println("Its a draw!");
            }
            else if (win) {
                System.out.println("You won!");
            }
            else {
                System.out.println("You lost!");
            }
        }
    }
}
