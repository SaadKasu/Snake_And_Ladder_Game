package SnakeAndLadder.Stratergies;

import SnakeAndLadder.Models.Player;

import java.util.List;

public interface PlayerOrderStratergy {
    public Player getNextPlayer(List<Player> playerList);
}
