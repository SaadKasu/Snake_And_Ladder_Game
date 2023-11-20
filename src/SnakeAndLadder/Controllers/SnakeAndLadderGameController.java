package SnakeAndLadder.Controllers;

import SnakeAndLadder.DTOs.*;
import SnakeAndLadder.Models.*;
import SnakeAndLadder.Services.SnakeAndLadderGameService;
import SnakeAndLadder.Stratergies.*;

import java.util.List;

public class SnakeAndLadderGameController {

    public CreateGameResponseDTO createGame(CreateGameRequestDTO requestDTO){
        Board board = SnakeAndLadderGameService.createBoard(requestDTO);
        List<Player> players = SnakeAndLadderGameService.createPlayers(requestDTO);
        PlayerOrderStratergy playerOrderStratergy = new FirstComeFirstServeStratergy(players.size());
        GameStartStratergy gameStartStratergy = new StartOnlyIfSixFirst();
        GameWinningStratergy gameWinningStratergy = new ReachLastCellStratergy();
        Dice dice = new Dice();
        dice.setNoOfDie(requestDTO.noOfDies);

        SnakeAndLadderGame snakeAndLadderGame = new SnakeAndLadderGame();
        snakeAndLadderGame.setBoard(board);
        snakeAndLadderGame.setGameStartStratergy(gameStartStratergy);
        snakeAndLadderGame.setGameWinningStratergy(gameWinningStratergy);
        snakeAndLadderGame.setPlayerOrder(playerOrderStratergy);
        snakeAndLadderGame.setPlayers(players);
        snakeAndLadderGame.setDice(dice);

        CreateGameResponseDTO responseDTO = new CreateGameResponseDTO();
        responseDTO.game = snakeAndLadderGame;

        return responseDTO;
    }

    public NextMoveResponseDTO nextPlayerMakeMove(NextMoveRequestDTO requestDTO){
        NextMoveResponseDTO responseDTO = SnakeAndLadderGameService.nextMove(requestDTO);
        CheckIfPlayerFoundSnakeOrLadderRequestDTO checkIfPlayerFoundSnakeOrLadderRequestDTO = new CheckIfPlayerFoundSnakeOrLadderRequestDTO();
        checkIfPlayerFoundSnakeOrLadderRequestDTO.player = responseDTO.player;
        checkIfPlayerFoundSnakeOrLadderRequestDTO.game = requestDTO.game;
        CheckIfPlayerFoundSnakeOrLadderResponseDTO checkIfPlayerFoundSnakeOrLadderResponseDTO = new CheckIfPlayerFoundSnakeOrLadderResponseDTO();
        checkIfPlayerFoundSnakeOrLadderResponseDTO = SnakeAndLadderGameService.checkIfPlayerFoundSnakeOrLadder(checkIfPlayerFoundSnakeOrLadderRequestDTO);
        return responseDTO;
    }

    public GameStatus checkIfGameIsCompleted(CheckGameWinnerRequestDTO requestDTO){
        return SnakeAndLadderGameService.checkIfGameIsCompleted(requestDTO);
    }
}
