package SnakeAndLadder.Models;

import SnakeAndLadder.Stratergies.GameStartStratergy;
import SnakeAndLadder.Stratergies.GameWinningStratergy;
import SnakeAndLadder.Stratergies.PlayerOrderStratergy;

import java.util.List;

public class SnakeAndLadderGame extends BaseClass{
    private Board board;
    private List<Player> players;
    private PlayerOrderStratergy playerOrder;
    private GameWinningStratergy gameWinningStratergy;
    private GameStartStratergy gameStartStratergy;
    private Dice dice;

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public PlayerOrderStratergy getPlayerOrder() {
        return playerOrder;
    }

    public void setPlayerOrder(PlayerOrderStratergy playerOrder) {
        this.playerOrder = playerOrder;
    }

    public GameWinningStratergy getGameWinningStratergy() {
        return gameWinningStratergy;
    }

    public void setGameWinningStratergy(GameWinningStratergy gameWinningStratergy) {
        this.gameWinningStratergy = gameWinningStratergy;
    }

    public GameStartStratergy getGameStartStratergy() {
        return gameStartStratergy;
    }

    public void setGameStartStratergy(GameStartStratergy gameStartStratergy) {
        this.gameStartStratergy = gameStartStratergy;
    }

    public Dice getDice() {
        return dice;
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }
}
