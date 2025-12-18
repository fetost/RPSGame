package game;

public class TypeOfPlay {
    private final String userMove;
    private final String opponentMove;
   

    public TypeOfPlay(String userMove, String opponentMove) {
        this.userMove = userMove.toLowerCase(); // do lower case here already to make sure that the whole class is using lower case
        this.opponentMove = opponentMove.toLowerCase();
    }
    
    public boolean determineWin() throws ValidationException {
        if (!userMove.equals("rock") && !userMove.equals("paper") && !userMove.equals("scissors")) {
            throw new ValidationException("You did not type any of the valid options");
        }
        if (userMove.equals("rock") && opponentMove.equals("paper")) {
            return false;
        }
        if (userMove.equals("paper") && opponentMove.equals("scissors")) {
            return false;
        }
        if (userMove.equals("scissors") && opponentMove.equals("rock")) {
            return false;
        }
        return true;
       
    }

}
