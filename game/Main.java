package game;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;


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

        String choice; // declared outside of while and if, due to value being needed later for connection. 
        while (true) {
            System.out.println("Are you hosting? (y/n)");
            System.out.print("> ");
            choice = scanner.nextLine().trim().toLowerCase();
            if (choice.equals("y") || choice.equals("n")) {
                break;
            }
            System.out.println("Type either y or n");
            System.out.println("---------------------");
        }

        String userName; // declared outside of while and if, due to value being needed later for connection. 
        while (true) {
            System.out.println("Type you username");
            System.out.print("> ");
            userName = scanner.nextLine().trim();
            if (!userName.isEmpty()) {
                break;
            }
            System.out.println("Username cannot be empty");
            System.out.println("---------------------");
        } 
        
        String host = null; // declared outside of while and if, due to value being needed later for connection. 
        if (choice.equals("n")) {
            while (true) {
                System.out.println("Enter host IP adress");
                System.out.println("> ");
                host = scanner.nextLine().trim();
                if (!host.isEmpty()) {
                    break;
                }
                System.out.println("IP cannot be empty");
                System.out.println("---------------------");
            }
        }
        ConnectionManager cm = new ConnectionManager(choice, userName, host);
        cm.setUpConnection();

        while (true) {
            System.out.println("----------------------------");
            System.out.println("Enter one of the moves below:");
            System.out.println("Rock" + "\n" + "Paper" + "\n" + "Scissors");
            System.out.print("> ");

            String userMove = scanner.nextLine().trim().toLowerCase(); 
            if (userMove.equalsIgnoreCase("quit")) {
                cm.closeSocket();
                System.out.println("You quit the game");
                break;
            }
            else if (userMove.isEmpty() || (!userMove.equals("rock") && !userMove.equals("paper") && !userMove.equals("scissors"))) { // have to use isEmpty because nextLine() is never null
                System.out.println("Type either rock, paper or scissors");
                continue;
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
