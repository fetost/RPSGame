package game;

/**
 * Determines the outcome of a Rock-Paper-Scissors round.
 * <p>
 * Stores the player’s move and the opponent’s move and
 * evaluates whether the player has won the round.
 * </p>
 */

public class TypeOfPlay {
    private final String userMove;
    private final String opponentMove;
   
    /**
     * Creates a new TypeOfPlay instance.
     *
     * @param userMove the move chosen by the player
     * @param opponentMove the move chosen by the opponent
     */
    public TypeOfPlay(String userMove, String opponentMove) {
        this.userMove = userMove.toLowerCase(); // do lower case here already to make sure that the whole class is using lower case
        this.opponentMove = opponentMove.toLowerCase();
    }

    /**
     * Determines whether the player has won the round.
     *
     * @return {@code true} if the player wins, {@code false} otherwise
     * @throws ValidationException if the player's move is invalid
     */
    
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
