package SnakeAndLadder.DTOs;

import SnakeAndLadder.Models.Board;
import SnakeAndLadder.Models.Dice;
import SnakeAndLadder.Models.Player;

public class CheckGameStartRequestDTO {
    private Board board;

    private Dice dice;

    private Player player;

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Dice getDice() {
        return dice;
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
