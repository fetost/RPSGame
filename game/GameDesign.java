package game;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * Provides all user interface dialogs for the Rock-Paper-Scissors game.
 * <p>
 * This class is stateless and consists entirely of static methods that
 * display dialogs using {@link JOptionPane}. It handles user input,
 * waiting screens, and game result messages.
 * </p>
 */
public class GameDesign {

    /**
     * Displays a dialog asking whether the user is hosting the game.
     *
     * @return {@code true} if the user chooses to host, {@code false} otherwise
     * @throws SystemExit if the dialog is closed by the user
     */
    public static boolean isHosting() {
        int hostChoice = JOptionPane.showConfirmDialog(
            null, 
            "Are you hosting the game?",
            "Host", 
            JOptionPane.YES_NO_OPTION);
        
        if (hostChoice == JOptionPane.CLOSED_OPTION) {
            System.exit(0); // user closed the dialog window
        }
        if (hostChoice == JOptionPane.YES_OPTION) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Prompts the user to enter a username.
     * <p>
     * The dialog will continue to appear until a non-empty username
     * is provided.
     * </p>
     *
     * @return the validated username entered by the user
     */
    public static String setUsername() {
        String nameChoice;
        while (true) {
            nameChoice = JOptionPane.showInputDialog("Enter your username");
            if (!nameChoice.isEmpty()) {
                break;
            }
            JOptionPane.showMessageDialog(null, "Username cannot be empty");
        }
        return nameChoice;
    }

    /**
     * Prompts the user to enter the host IP address when joining a game.
     * <p>
     * The dialog will repeat until a non-empty IP address is entered.
     * </p>
     *
     * @return the host IP address
     */
    public static String isNotHosting() {
        String isNotHost;
        while (true) {
            isNotHost = JOptionPane.showInputDialog(null, "Enter host IP adress");
            if (!isNotHost.isEmpty()) {
                break;
            }
            JOptionPane.showMessageDialog(null, "IP cannot be empty");
        }
        return isNotHost;
    }

    /**
     * Displays a non-modal waiting screen for the server while
     * waiting for a client to connect.
     *
     * @param ip the host IP address to display
     * @return the {@link JDialog} instance so it can be closed later via dispose()
     */
    public static JDialog showServerWaitingScreen(String ip) {
        JOptionPane pane = new JOptionPane( // have to open instance due to modifications.
                "Waiting for a player to join...\nHost IP: " + ip,
                JOptionPane.INFORMATION_MESSAGE,
                JOptionPane.DEFAULT_OPTION,
                null,
                new Object[]{}, // remove buttons
                null
        );

        JDialog dialog = pane.createDialog(null, "Waiting...");
        dialog.setModal(false); // non-blocking
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setVisible(true);

        return dialog; 
    }

    /**
     * Displays a non-modal waiting screen for the client while
     * attempting to connect to the server.
     *
     * @return the {@link JDialog} instance so it can be closed later via dispose()
     */
    public static JDialog showClientWaitingScreen() {
        JOptionPane pane = new JOptionPane(
                "Connecting to server...",
                JOptionPane.INFORMATION_MESSAGE,
                JOptionPane.DEFAULT_OPTION,
                null,
                new Object[]{}, // remove buttons
                null
        );

        JDialog dialog = pane.createDialog(null, "Waiting...");
        dialog.setModal(false);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setVisible(true);

        return dialog; 
    }

    /**
     * Displays a non-modal waiting screen while waiting for
     * the opponent to make a move.
     *
     * @return the {@link JDialog} instance so it can be closed later via dispose()
     */
    public static JDialog showGeneralWaitingScreen() {
        JOptionPane pane = new JOptionPane(
                "Waiting for opponent...",
                JOptionPane.INFORMATION_MESSAGE,
                JOptionPane.DEFAULT_OPTION,
                null,
                new Object[]{}, // remove buttons
                null
        );

        JDialog dialog = pane.createDialog(null, "Waiting...");
        dialog.setModal(false);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setVisible(true);

        return dialog; // return so we can close it later.
    }

    /**
     * Displays the main game screen where the user chooses a move.
     *
     * @return an integer representing the user's choice:
     *         <ul>
     *           <li>0 = Rock</li>
     *           <li>1 = Paper</li>
     *           <li>2 = Scissors</li>
     *           <li>-1 = Quit or dialog closed</li>
     *         </ul>
     */
    public static int gameScreen() {
        Object[] options = {"Rock", "Paper", "Scissors", "Quit"};
        int usermove;
        while (true) {
            usermove = JOptionPane.showOptionDialog(
                null,
                "Choose your move",
                "RPS",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
            );
            if (usermove == 0 || usermove == 1 || usermove == 2) {
                break;
            }
            else if (usermove == JOptionPane.CLOSED_OPTION || usermove == 3) {
                return -1;
            }
        }
        return usermove;
    }

    /**
     * Displays a message indicating that the opponent has left the game.
     */
    public static void ifOpponentQuit() {
        String quitMessage = "Your opponent has left the game";
        JOptionPane.showMessageDialog(null, quitMessage);

    }

    /**
     * Displays a result message showing both players' moves.
     *
     * @param usermove the local player's move
     * @param opponentMove the opponent's move
     * @param username the local player's username
     * @param opponentUsername the opponent's username
     */
    public static void isDraw (String usermove, String opponentMove, String username, String opponentUsername) {
        String drawMessage = username + " move: " + usermove + "\n" 
                            + opponentUsername + " move: " + opponentMove + "\n"
                             + "It's a draw!";
        JOptionPane.showMessageDialog(null, drawMessage);
    }

    public static void isWin (String usermove, String opponentMove, String username, String opponentUsername) {
        String winMessage = username + " move: " + usermove + "\n" 
                            + opponentUsername + " move: " + opponentMove + "\n"
                             + "You won!";
        JOptionPane.showMessageDialog(null, winMessage);
    }

    public static void isLoss (String usermove, String opponentMove, String username, String opponentUsername) {
        String lossMessage = username + " move: " + usermove + "\n" 
                            + opponentUsername + " move: " + opponentMove + "\n"
                             + "You lost!";
        JOptionPane.showMessageDialog(null, lossMessage);
    }
    
}

