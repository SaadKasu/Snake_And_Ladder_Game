package SnakeAndLadder.Client;

import SnakeAndLadder.Controllers.ClientController;
import SnakeAndLadder.Models.GameStatus;

import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        System.out.println("Hi, welcome to the Snakes and Ladders Game");
        ClientController controller = new ClientController();
        Scanner inp = new Scanner(System.in);
        System.out.println("Please enter the number of rows and columns on the board - ");
        int size = inp.nextInt();
        inp.nextLine();
        controller.setSizeOfBoard(size);
        System.out.println("Please enter the number of dies on the board - ");
        int noOfDies = inp.nextInt();
        inp.nextLine();
        controller.setNoOfDies(noOfDies);
        System.out.println("Please enter the number of people in the Game - ");
        int numberOfPlayers = inp.nextInt();
        inp.nextLine();
        while (numberOfPlayers-- > 0){
            System.out.println("Please Enter the name of the next Player - ");
            String name = inp.nextLine();
            controller.addPlayerToGame(name);
        }
        System.out.println("Please enter the number of Snakes on the Board - ");
        int numberOfSnakes = inp.nextInt();
        inp.nextLine();
        while (numberOfSnakes-- > 0){
            System.out.println("Please Enter the head cell number of the next Snake - ");
            int headCell = inp.nextInt();
            System.out.println("Please Enter the tail cell number of the next Snake - ");
            int tailCell = inp.nextInt();
            controller.addSnakeToBoard(headCell,tailCell);
        }

        System.out.println("Please enter the number of Ladders on the Board - ");
        int numberOfLadders = inp.nextInt();
        inp.nextLine();
        while (numberOfLadders-- > 0){
            System.out.println("Please Enter the start cell number of the next Ladder - ");
            int startCell = inp.nextInt();
            System.out.println("Please Enter the end cell number of the next Ladder - ");
            int endCell = inp.nextInt();
            controller.addSnakeToBoard(startCell,endCell);
        }
        controller.createSnakeAndLadderGame();
        inp.nextLine();
        System.out.println("Would you like to start the game ? (Y/N)");
        String gameStart = inp.nextLine();
        if ("Y".equalsIgnoreCase(gameStart)){
            while (true){
                System.out.println("Press Any key to roll the die - ");
                inp.nextLine();
                GameStatus gameStatus = controller.makeNextMove();
                if (gameStatus == GameStatus.COMPLETED)
                    break;
            }
        }
    }
}
