package SnakeAndLadder.Stratergies;

import SnakeAndLadder.DTOs.CheckGameWinnerRequestDTO;
import SnakeAndLadder.Models.Board;
import SnakeAndLadder.Models.GameStatus;

public interface GameWinningStratergy {
    public GameStatus checkIfGameWon(CheckGameWinnerRequestDTO requestDTO);
}
