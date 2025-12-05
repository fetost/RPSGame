package game;

import java.util.Random;
import java.util.Scanner;

// TODO: 1. Check exactly how the object initialzing works. 
// TODO: 2. Make validation for the input field. 
// TODO: 3. Implement another player through sockets instead of computer.

public class Main {
    public static void main(String[] args) throws ValidationException {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random(); // Random here so its not made each iteration
        System.out.println("Enter one of the moves below:");
        System.out.println("Rock" + "\n" + "Paper" + "\n" + "Scissors");
        
        while (true) {
            System.out.print("> ");
            String userMove = scanner.nextLine(); 

            if (userMove.isEmpty()) { // have to use isEmpty because nextLine() is never null
                throw new ValidationException("Type something idiot");
            }
            
            String[] rps = {"Rock", "Paper", "Scissors"};
            String computerMove = rps[random.nextInt(rps.length)];
            System.out.println("User move is: " + userMove);
            System.out.println("Computer move is: " + computerMove);

            TypeOfPlay play = new TypeOfPlay(userMove, computerMove);
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
