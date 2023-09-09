import java.util.Random;
import java.util.Scanner;
// this program is made as a tennis tournament where user enters the same of the program
// they also can put in any players they want and then there's 4 quarters and 2 semi-finals and after the final
// the winners are announced; the cool part is that the user can pick if they want to generate another outcome
public class Main {
    public static void main(String[] args) {
        String[] players = new String[8];
        Scanner scanner = new Scanner(System.in);

        System.out.println("--------------------------------------------------------");
        System.out.println("Welcome to Tennis Tournament Predictor Program");
        System.out.println("--------------------------------------------------------");
        // welcome message
        System.out.print("\nPlease enter a name for the tournament: ");
        String tournamentName = scanner.nextLine();
        // ask for user input to name the game

        System.out.println("\nPlease enter the 8 participating players:");
        for (int i = 0; i < players.length; i++) {
            players[i] = scanner.nextLine();
        }
        // storing the players into the array so start at 0 n cant exceed 8 therefore there's still 8 players

        Random random = new Random(); // create new instance so we can use it soon

        while (true) { // always true unless user says N and reach break
            System.out.println("\n------- " + tournamentName + " Tennis Tournament Predictions -------");
            // print tournament name
            shuffleArray(players);
            // this shuffles the players

            String[] quarterFinals = new String[4]; // having 4 quarters
            for (int i = 0; i < quarterFinals.length; i++) { // loop simulates quarter final match
                quarterFinals[i] = players[i]; // assigns name index i to corresponding quarter finals array ( finds winner)
                System.out.println("\nQuarter Final " + (i + 1) + ": " + players[i] + " Vs. " + players[i + 4]);
                System.out.println(players[i] + " wins !!!");
                // displaying the matches n pairs n then the winners
            }


            String[] semiFinals = new String[2]; // 2 semi-finals
            semiFinals[0] = quarterFinals[1]; // Winner of quarter final 2 to semi-finals array
            semiFinals[1] = quarterFinals[3]; // Winner of quarter final 4 to semi-finals array
            System.out.println("\nSemi Final 1: " + quarterFinals[2] + " Vs. " + quarterFinals[3]);
            System.out.println(semiFinals[0] + " wins !!!");
            // Displaying the match pairings and the winner of Semi Final 1

            semiFinals[0] = quarterFinals[0]; // Winner of quarter final 1 to semi-finals array
            semiFinals[1] = quarterFinals[2]; // Winner of quarter final 3 to semi-finals array
            System.out.println("\nSemi Final 2: " + quarterFinals[0] + " Vs. " + quarterFinals[2]);
            System.out.println(semiFinals[0] + " wins !!!");
            // Displaying the match pairings and the winner of Semi Final 2


            String[] finalMatch = new String[]{semiFinals[1], semiFinals[0]}; // Players swapped for correct order
            System.out.println("\nFinal: " + finalMatch[0] + " Vs. " + finalMatch[1]); // display the final round match
            int winnerIndex = random.nextInt(2); // Randomly pick the winner
            System.out.println(finalMatch[winnerIndex] + " wins !!!"); // display winner

            System.out.print("\nDo you want a different outcome? (Y or n): ");
            String continueChoice = scanner.nextLine();
            //ask user if they want to change the outcomes if yes then still true and if N then break n leave while loop
            if (!continueChoice.equalsIgnoreCase("Y")) {
                break;
            }
        }

        System.out.println("\nThank you for using the Tennis Tournament Predictor Program");
        // display thank you message terminate
    }

    private static void shuffleArray(String[] array) {
        Random random = new Random(); // so we can shuffle
        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            String temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        //so each player can get a turn
        // for loop iterates array reversely so from last to first and inside generate random J to swap with index i
        // temp is where i is temporarily stored then i n j swap
    }
}
