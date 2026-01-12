package game;

import javax.swing.JDialog;
import javax.swing.JOptionPane;


public class GameDesign {
    
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

    public static JDialog showServerWaitingScreen(String ip) {
        JOptionPane pane = new JOptionPane(
                "Waiting for a player to join...\nHost IP: " + ip,
                JOptionPane.INFORMATION_MESSAGE,
                JOptionPane.DEFAULT_OPTION,
                null,
                new Object[]{}, // remove buttons
                null
        );

        JDialog dialog = pane.createDialog(null, "Server Waiting...");
        dialog.setModal(false); // non-blocking
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setVisible(true);

        return dialog; // return so we can close it later.
    }

    // Client waiting screen
    public static JDialog showClientWaitingScreen() {
        JOptionPane pane = new JOptionPane(
                "Connecting to server...",
                JOptionPane.INFORMATION_MESSAGE,
                JOptionPane.DEFAULT_OPTION,
                null,
                new Object[]{}, // remove buttons
                null
        );

        JDialog dialog = pane.createDialog(null, "Client Waiting...");
        dialog.setModal(false);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setVisible(true);

        return dialog; // return so we can close it later.
    }

    public static JDialog showGeneralWaitingScreen() {
        JOptionPane pane = new JOptionPane(
                "Waiting for opponent...",
                JOptionPane.INFORMATION_MESSAGE,
                JOptionPane.DEFAULT_OPTION,
                null,
                new Object[]{}, // remove buttons
                null
        );

        JDialog dialog = pane.createDialog(null, "Client Waiting...");
        dialog.setModal(false);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setVisible(true);

        return dialog; // return so we can close it later.
    }

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

    public static void ifOpponentQuit() {
        String quitMessage = "Your opponent has left the game";
        JOptionPane.showMessageDialog(null, quitMessage);

    }

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

