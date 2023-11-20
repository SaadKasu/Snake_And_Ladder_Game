package SnakeAndLadder.Stratergies;

import SnakeAndLadder.Models.Player;

import java.util.List;

public class FirstComeFirstServeStratergy implements PlayerOrderStratergy{
    private int currentPlayerIndex, totalPlayers;
    public FirstComeFirstServeStratergy(int totalPlayers){
        currentPlayerIndex = 0;
        this.totalPlayers = totalPlayers;
    }

    @Override
    public Player getNextPlayer(List<Player> playerList) {
        currentPlayerIndex %= totalPlayers;
        return playerList.get(currentPlayerIndex++);
    }
}
