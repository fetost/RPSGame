package game;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

// TODO: check if I should trim here or in type of play
// TODO: imlement validation for edge cases for server/client connection
// TODO: double check the logic of the server/client connection
// TODO: test the connection on a different computer 
// TODO: implement an interface instead of using the terminal

public class Main {
    public static void main(String[] args) throws ValidationException, IOException {       
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type either host or client");
        System.out.print("> ");
        
        // Instances are declared here so its not made each iteration
        Random random = new Random(); 
        ConnectionManager cm;

        String choice = scanner.nextLine().trim().toLowerCase(); 
        if (choice.equals("host")) { 
            cm = new ConnectionManager(true);
            cm.setUpConnection();
        }
        else if (choice.equals("client")) {
            cm = new ConnectionManager(false);
            cm.setUpConnection();
        }
        else {
            throw new ValidationException("You need to type either host or client");
        }
    
        while (true) {
            System.out.println("----------------------------");
            System.out.println("Enter one of the moves below:");
            System.out.println("Rock" + "\n" + "Paper" + "\n" + "Scissors");
            System.out.print("> ");

            String userMove = scanner.nextLine(); 
            cm.sendMessage(userMove);
            System.out.println("Waiting for opponent...");
            String opponentMove = cm.receiveMessage();
            if (userMove.isEmpty()) { // have to use isEmpty because nextLine() is never null
                throw new ValidationException("Type something idiot");
            }

            System.out.println("User move is: " + userMove);
            System.out.println("Opponent move is: " + opponentMove);

            /* 
            Creating an instance (object) of the TypeOfPlay class.
            The constructor (TypeOfPlay) is called and gets the data.
            Now the "play" variable knows what the moves are, and can call determineWin() using the stored data. 
            */
            TypeOfPlay play = new TypeOfPlay(userMove, opponentMove); 
            boolean win = play.determineWin();

            if (win) {
                System.out.println("You won!");
            }
            else {
                System.out.println("You lost!");
            }
        }
    }
}
