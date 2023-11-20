package SnakeAndLadder.Stratergies;

import SnakeAndLadder.Controllers.DieController;
import SnakeAndLadder.DTOs.CheckGameStartRequestDTO;
import SnakeAndLadder.Models.Board;
import SnakeAndLadder.Models.Dice;
import SnakeAndLadder.Models.GameStart;
import SnakeAndLadder.Models.Player;

import java.util.Map;

public class StartOnlyIfSixFirst implements GameStartStratergy{
    @Override
    public GameStart canPlayerStartTheGame(CheckGameStartRequestDTO requestDTO) {
        Dice dice = requestDTO.getDice();
        DieController dieController = new DieController();
        return dieController.generateDiceNumber(dice) == 6 ? GameStart.START : GameStart.DONT_START;
    }
}
