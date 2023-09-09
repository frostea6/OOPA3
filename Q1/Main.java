// this program gets two players to play tic tac toe against eachother and
//it checks the user input if its spot is already taken or doesnt exist to make sure the
//game runs smoothly

import java.util.Scanner; // so that we can use the Scanner later user input

public class Main {
    private static final int BOARD_SIZE = 3; //board size 3X3
    private static final char EMPTY = '_'; // empty spot in the game

    private char[][] board; //array to store spots
    private String playerA; // store name of player 1
    private String playerB; // store name player 2
    private Scanner scanner; // declare Scanner object so can read input

    public Main() { // constructor creates empty board game
        board = new char[BOARD_SIZE][BOARD_SIZE]; // initialize 2D array with char so creates 3X3
        scanner = new Scanner(System.in); // allows program to read user input
    }

    public void playGame() { // for the game being played process
        System.out.println("--------------------------------");
        System.out.println("Welcome to Tic-tac-Toe Game");
        System.out.println("--------------------------------");
        //welcome message
        System.out.println("Please enter the names of the two players");
        playerA = scanner.nextLine();
        playerB = scanner.nextLine();
        // get user input names

        boolean playAgain = true;

        while (playAgain) { // always true unless player doesnt want to play and you get a break
            initializeBoard(); // board initialized

            boolean gameFinished = false; // the ongoing game
            char currentPlayer = 'A'; // player A starts

            while (!gameFinished) { // runs until game is finished
                displayBoard(); // displays board game

                System.out.println(playerName(currentPlayer) + " will start the game. Please choose a spot.");
                int row = getValidInput("Row: ");
                int col = getValidInput("Column: ");
                // get user to put in spot and calls getValid method

                if (makeMove(row, col, currentPlayer)) { // method called so we can start n if theres a free space then continue
                    if (checkWin()) { // see if current player has won
                        displayBoard();
                        System.out.println(playerName(currentPlayer) + " wins!!!");
                        gameFinished = true;
                        // says game has ended if they win n you exit
                    } else if (checkDraw()) { // if both players draw
                        displayBoard();
                        System.out.println("It's a draw!");
                        gameFinished = true;
                        //exit
                    } else { // switch turn to other player by updating the value of currentPlayer so its fair play
                        currentPlayer = currentPlayer == 'A' ? 'B' : 'A';
                    }
                }
            }

            System.out.print("\nDo you want to play another game? (y/n): ");
            // prompt user if wanna play again
            String choice = scanner.nextLine().trim().toLowerCase();
            // user input stored in choice and make sure its not case sensitive and get rid of white space

            if (choice.equals("n")) {
                playAgain = false; // will exit loop
            }
        }

        System.out.println("\nThank you for playing the Tic-Tac-Toe game!!!");
        //thank you message to say bye
    }

    private void initializeBoard() { // have empty spots in the beginning
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }
        // loop iterates over each row and column of the borad array  and outer does row and inner is columns
        // assign empty to spots in board game which is just _
    }

    private void displayBoard() { // gives current state
        System.out.println("Current state of the board");
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(" " + board[i][j] + " ");
            }
            System.out.println();
        }
        // loop iterates each row and column of board array
        // i index current row from 0 to 1 size n j is same but column n at the end we concatenate current spots with spaces
    }

    private String playerName(char player) {
        return (player == 'A') ? playerA : playerB;
    }
    // display player name so playerA always starts n then switch

    private int getValidInput(String prompt) { // retrieves valid input
        int value;
        while (true) { // always runs until get value
            try {
                System.out.print(prompt);
                value = Integer.parseInt(scanner.nextLine()); // gets user input as string and converts to int
                if (value >= 0 && value < BOARD_SIZE) { // if the value within range 0 to borad size then its valid  and exits
                    break;
                } else {
                    System.out.println("Invalid spot. Row and column numbers cannot exceed " + (BOARD_SIZE - 1) + ".");
                } // loop will continu to ask player for new value
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }// if put non numerical value then it will ask for actual input
        }
        return value; // return value to caller
    }
    private boolean makeMove(int row, int col, char currentPlayer) { // for mking new moves
        if (board[row][col] != EMPTY) {
            System.out.println("Spot has already been taken.");
            return false;
        }
        board[row][col] = (currentPlayer == 'A') ? 'X' : 'O'; // move execution so A = x and B = 0
        return true;
    }
    // comapres board[row][col] with constant EMPTY so if spots taken then says spots already taken
    private boolean checkWin() { // checks winner
        // Check rows
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][0] != EMPTY && board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
                return true;
            }
        }
        // for each row checks first element board[i][0] if its empty or notand its it has a 1 for second element
        //and it has a 2 for 3rd element which if all are filled then player wins so you get true

        // Check columns
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[0][i] != EMPTY && board[0][i] == board[1][i] && board[0][i] == board[2][i]) {
                return true;
            }
        }// same logic is applied here as for the rows

        // Check diagonals
        if (board[0][0] != EMPTY && board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            return true;
        }

        if (board[0][2] != EMPTY && board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
            return true;
        } // same logic for the rows and columns is applied here

        return false; // if no winners
    }

    private boolean checkDraw() { // see if its a draw
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    } // check if value is equal to empty n if it is then its not a draw so goes to false
    // if the method completes the iteration without finding empty spots on the board without wins then goes to true

    public static void main(String[] args) {
        Main game = new Main();
        game.playGame();
    } // declares name of the game n creates new instance of the main class by calling main constructor and assigns it to game
    //gets playGame method on game object n continues for playGame until finished
}