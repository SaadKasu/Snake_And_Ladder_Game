package SnakeAndLadder.Stratergies;

import SnakeAndLadder.DTOs.CheckGameWinnerRequestDTO;
import SnakeAndLadder.Models.Board;
import SnakeAndLadder.Models.GameStatus;
import SnakeAndLadder.Models.Player;
import SnakeAndLadder.Models.SnakeAndLadderGame;

import java.util.List;

public class ReachLastCellStratergy implements GameWinningStratergy{
    @Override
    public GameStatus checkIfGameWon(CheckGameWinnerRequestDTO requestDTO) {
        SnakeAndLadderGame game = requestDTO.game;
        List<Player> playerList = game.getPlayers();
        Board board = game.getBoard();
        for (Player player : playerList)
            if (player.getPreviousMoves().size() > 0 && player.getLastMove().getToCell().getCellNumber() == board.getNumberOfColumns()*board.getNumberOfRows())
                return GameStatus.COMPLETED;
        return GameStatus.IN_PROGRESS;
    }
}
