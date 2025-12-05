package game;

public class TypeOfPlay {
    private final String userMove;
    private final String computerMove;
   

    public TypeOfPlay(String userMove, String computerMove) {
        this.userMove = userMove.toLowerCase(); // do lower case here already to make sure that the whole class is using lower case
        this.computerMove = computerMove.toLowerCase();
    }
    
    public boolean determineWin() throws ValidationException {
        if (!userMove.equals("rock") && !userMove.equals("paper") && !userMove.equals("scissors")) {
            throw new ValidationException("You did not type any of the valid options");
        }
        if (userMove.equals("rock") && computerMove.equals("paper")) {
            return false;
        }
        if (userMove.equals("paper") && computerMove.equals("scissors")) {
            return false;
        }
        if (userMove.equals("scissors") && computerMove.equals("rock")) {
            return false;
        }
        if (userMove.equals(computerMove)) {
            System.out.println("Draw!");
        }
        return true;
       
    }

}
