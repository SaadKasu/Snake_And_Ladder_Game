package SnakeAndLadder.DTOs;

import java.util.List;

public class CreateGameRequestDTO {
    public int rows, columns;
    public List<Integer> snakeHeadCellNumber, snakeTailCellNumber;
    public List<Integer> ladderStartCellNumber, ladderEndCellNumber;
    public int noOfDies;
    public List<String> playerNames;
}
