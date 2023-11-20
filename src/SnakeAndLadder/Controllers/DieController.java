package SnakeAndLadder.Controllers;

import SnakeAndLadder.Models.Dice;

import java.util.Random;

public class DieController {

    public int generateDiceNumber(Dice dice){
        int dieCount = dice.getNoOfDie();
        Random random = new Random();

        int randomNumber =  random.nextInt(dieCount*6 + 1);
        System.out.println("The die produced the number - "+Math.max(randomNumber, 1));
        return Math.max(randomNumber, 1);
    }


}
