package SnakeAndLadder.Stratergies;

import SnakeAndLadder.DTOs.CheckGameStartRequestDTO;
import SnakeAndLadder.Models.GameStart;

public interface GameStartStratergy {
    public GameStart canPlayerStartTheGame(CheckGameStartRequestDTO requestDTO);
}
