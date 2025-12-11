package game;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

// TODO: imlement validation for edge cases for server/client connection
// TODO: double check the logic of the server/client connection
// TODO: check if the logic of choosing server/client can be moved to a different class or if its even necessary
// TODO: test the connection on a different computer 
// TODO: implement an interface instead of using the terminal

public class Main {
    public static void main(String[] args) throws ValidationException, IOException {       
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type either host or client");
        System.out.print("> ");
        String choice = scanner.nextLine().trim().toLowerCase(); 
        
        // Instances are declared here so its not made each iteration
        Random random = new Random(); 
        ConnectionManager cm = new ConnectionManager(choice);
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
            
            System.out.println("Your move is: " + userMove);
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
