package SnakeAndLadder.Controllers;

import SnakeAndLadder.DTOs.CheckGameWinnerRequestDTO;
import SnakeAndLadder.DTOs.CreateGameRequestDTO;
import SnakeAndLadder.DTOs.CreateGameResponseDTO;
import SnakeAndLadder.DTOs.NextMoveRequestDTO;
import SnakeAndLadder.Models.GameStatus;
import SnakeAndLadder.Models.SnakeAndLadderGame;

import java.util.ArrayList;
import java.util.List;

public class ClientController {
    private List<Integer> snakeHeadsCellNumber, snakeTailsCellNumber, ladderStartCellNumber, ladderEndCellNumber;
    private int noOfRows, noOfColumns;
    private int noOfDies;
    private List<String> playerNames;
    private SnakeAndLadderGame game;
    private SnakeAndLadderGameController gameController;

    public ClientController(){
        snakeHeadsCellNumber = new ArrayList<>();
        snakeTailsCellNumber = new ArrayList<>();
        ladderEndCellNumber =  new ArrayList<>();
        ladderStartCellNumber = new ArrayList<>();
        playerNames = new ArrayList<>();
    }

    public void setSizeOfBoard(int size){
        this.noOfRows = this.noOfColumns = size;
    }

    public void setNoOfDies(int dies){
        this.noOfDies = dies;
    }

    public  void addSnakeToBoard(int snakeHeadCellNumber, int snakeTailCellNumber){
        snakeHeadsCellNumber.add(snakeHeadCellNumber);
        snakeTailsCellNumber.add(snakeTailCellNumber);
    }

    public  void addLadderToBoard(int ladderStartCellNumber, int ladderEndCellNumber){
        this.ladderStartCellNumber.add(ladderStartCellNumber);
        this.ladderStartCellNumber.add(ladderEndCellNumber);
    }

    public void createSnakeAndLadderGame(){
        SnakeAndLadderGameController gameController = new SnakeAndLadderGameController();
        CreateGameRequestDTO requestDTO = new CreateGameRequestDTO();
        requestDTO.columns = noOfColumns;
        requestDTO.rows = noOfRows;
        requestDTO.ladderEndCellNumber = ladderEndCellNumber;
        requestDTO.ladderStartCellNumber = ladderStartCellNumber;
        requestDTO.noOfDies = noOfDies;
        requestDTO.playerNames = playerNames;
        requestDTO.snakeHeadCellNumber = snakeHeadsCellNumber;
        requestDTO.snakeTailCellNumber = snakeTailsCellNumber;

        CreateGameResponseDTO responseDTO = gameController.createGame(requestDTO);
        this.gameController = gameController;
        game =  responseDTO.game;
    }

    public void addPlayerToGame(String name){
        playerNames.add(name);
    }

    public GameStatus makeNextMove(){
        NextMoveRequestDTO requestDTO = new NextMoveRequestDTO();
        requestDTO.game = this.game;
        gameController.nextPlayerMakeMove(requestDTO);
        CheckGameWinnerRequestDTO checkGameWinnerRequestDTO = new CheckGameWinnerRequestDTO();
        checkGameWinnerRequestDTO.game = this.game;
        GameStatus gameStatus = gameController.checkIfGameIsCompleted(checkGameWinnerRequestDTO);
        return gameStatus;
    }



}
