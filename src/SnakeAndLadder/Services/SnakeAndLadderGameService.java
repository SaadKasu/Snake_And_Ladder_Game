package SnakeAndLadder.Services;

import SnakeAndLadder.Controllers.DieController;
import SnakeAndLadder.DTOs.*;
import SnakeAndLadder.Models.*;
import SnakeAndLadder.Stratergies.GameStartStratergy;
import SnakeAndLadder.Stratergies.GameWinningStratergy;
import SnakeAndLadder.Stratergies.PlayerOrderStratergy;

import java.util.ArrayList;
import java.util.List;


public class SnakeAndLadderGameService {
    private static DieController dieController = new DieController();
    public static Board createBoard(CreateGameRequestDTO requestDTO){
        Board board = new Board();
        int rows = requestDTO.rows;
        int columns = requestDTO.columns;
        board.setNumberOfColumns(columns);
        board.setNumberOfRows(rows);
        board.setCells(createCellsOnBoard(board));
        for (int i = 0;i<board.getCells().length;i++){
            System.out.println();
            for (int j = 0;j<board.getCells()[i].length;j++){
                System.out.print(board.getCells()[i][j].getCellNumber() + " ");
            }
        }
        board.setLadders(createLadders(requestDTO, board));
        board.setSnakes(createSnakes(requestDTO, board));
        return board;
    }

    static Cell[][] createCellsOnBoard(Board board){
        int rows = board.getNumberOfRows(),columns = board.getNumberOfColumns();
        Cell [][] cells = new Cell[rows][columns];
        boolean leftToRight = true;
        int cellCount = 1;
        for (int i = rows - 1;i>=0;i--){
            int j = leftToRight ? 0 : columns - 1;
            while((leftToRight && j < columns) || (!leftToRight && j >= 0)){
                cells[i][j] = new Cell();
                cells[i][j].setRow(rows - i);
                cells[i][j].setCol(leftToRight ? j + 1 : columns - j );
                cells[i][j].setCellNumber(cellCount++);
                j = leftToRight ? j + 1 : j - 1;
            }
            leftToRight = !leftToRight;
        }
        return cells;
    }

    public static List<Ladder> createLadders(CreateGameRequestDTO requestDTO, Board board){
        List<Integer> startCell = requestDTO.ladderStartCellNumber;
        List<Integer> endCell = requestDTO.ladderEndCellNumber;
        List<Ladder> ladders = new ArrayList<>();
        int size = startCell.size();
        for (int i = 0;i<size;i++){
            int start = startCell.get(i), end = endCell.get(i);
            Ladder ladder = new Ladder();
            ladder.setLadderStart(getCellBasedOnNumber(board,start));
            ladder.setLadderEnd(getCellBasedOnNumber(board,end));
            ladders.add(ladder);
        }
        return ladders;
    }

    public static List<Snake> createSnakes(CreateGameRequestDTO requestDTO, Board board){
        List<Integer> startCell = requestDTO.snakeHeadCellNumber;
        List<Integer> endCell = requestDTO.snakeTailCellNumber;
        List<Snake> snakes = new ArrayList<>();
        int size = startCell.size();
        for (int i = 0;i<size;i++){
            int start = startCell.get(i), end = endCell.get(i);
            Snake snake = new Snake();
            snake.setSnakeHead(getCellBasedOnNumber(board,start));
            snake.setSnakeTail(getCellBasedOnNumber(board,end));
            snakes.add(snake);
        }
        return snakes;
    }

    public static List<Player> createPlayers(CreateGameRequestDTO requestDTO){
        List<String> playerNames = requestDTO.playerNames;
        List<Player> players = new ArrayList<>();
        for (String name : playerNames){
            Player player = new Player();
            player.setName(name);
            player.setDieThrows(new ArrayList<>());
            player.setPreviousMoves(new ArrayList<>());
            players.add(player);
        }
        return players;
    }

    public static NextMoveResponseDTO nextMove(NextMoveRequestDTO requestDTO){
        SnakeAndLadderGame game = requestDTO.game;
        Player player = getNextPlayer(requestDTO);
        NextMoveResponseDTO responseDTO = new NextMoveResponseDTO();
        responseDTO.player = player;
        if (player.getPreviousMoves().size() == 0 ){
            if (canPlayerStartTheGame(player,game) == GameStart.START)
                makeFirstMove(player,game);
            else{
                System.out.println("Looks like "+player.getName()+" can not start the game just yet.");
            }
        }
        if (player.getPreviousMoves().size() > 0){
            makeMove(player, game);
        }
        return responseDTO;
    }

    public static CheckIfPlayerFoundSnakeOrLadderResponseDTO checkIfPlayerFoundSnakeOrLadder(CheckIfPlayerFoundSnakeOrLadderRequestDTO requestDTO){
        CheckIfPlayerFoundSnakeOrLadderResponseDTO responseDTO = new CheckIfPlayerFoundSnakeOrLadderResponseDTO();
        responseDTO.player = requestDTO.player;
        Player player = requestDTO.player;
        SnakeAndLadderGame game = requestDTO.game;
        if (player.getPreviousMoves().size() == 0){
            return responseDTO;
        }

        alterMoveIfSnakeOrLadderIsPresent(player, game);

        return responseDTO;
    }

    public static void alterMoveIfSnakeOrLadderIsPresent(Player player, SnakeAndLadderGame game){
        List<Snake> snakesOnBoard = game.getBoard().getSnakes();
        List<Ladder> laddersOnBoard = game.getBoard().getLadders();

        for (Snake snake : snakesOnBoard){
            if (checkIfSnakeHeadIsAtPlayerCell(player,snake)){
                addSnakeMoveToPlayer(player,snake);
                return;
            }
        }

        for (Ladder ladder : laddersOnBoard){
            if(checkIfLadderStartIsAtPlayerCell(player,ladder)){
                addLadderMoveToPlayer(player,ladder);
                return;
            }
        }
    }

    public static boolean checkIfSnakeHeadIsAtPlayerCell(Player player, Snake snake){
        Cell snakeHeadCell = snake.getSnakeHead();
        Cell playerCell = player.getLastMove().getToCell();

        return snakeHeadCell.getCellNumber() == playerCell.getCellNumber();
    }

    public static void addSnakeMoveToPlayer(Player player, Snake snake){
        Move move = new Move();
        Cell playerCell = player.getLastMove().getToCell();
        Cell snakeTailCell = snake.getSnakeTail();
        move.setToCell(snakeTailCell);
        move.setFromCell(playerCell);
        player.getPreviousMoves().add(move);
        System.out.println("Oh oh.... Looks like you have been bitten by the snake and have moved from Cell number - "+playerCell.getCellNumber()+" to Cell number - "+snakeTailCell.getCellNumber());
    }

    public static boolean checkIfLadderStartIsAtPlayerCell(Player player, Ladder ladder){
        Cell ladderStartCell = ladder.getLadderStart();
        Cell playerCell = player.getLastMove().getToCell();

        return ladderStartCell.getCellNumber() == playerCell.getCellNumber();
    }

    public static void addLadderMoveToPlayer(Player player, Ladder ladder){
        Move move = new Move();
        Cell playerCell = player.getLastMove().getToCell();
        Cell ladderEndCell = ladder.getLadderEnd();
        move.setToCell(ladderEndCell);
        move.setFromCell(playerCell);
        player.getPreviousMoves().add(move);
        System.out.println("Great news, you have found a ladder and have moved from Cell number - "+playerCell.getCellNumber()+" to Cell number - "+ladderEndCell.getCellNumber());
    }

    public static Player getNextPlayer(NextMoveRequestDTO requestDTO){
        SnakeAndLadderGame game = requestDTO.game;
        PlayerOrderStratergy playerOrderStratergy = game.getPlayerOrder();
        Player player = playerOrderStratergy.getNextPlayer(game.getPlayers());
        return player;
    }

    public static void makeFirstMove(Player player, SnakeAndLadderGame game){
        Move move = new Move();
        Cell toCell = getCellBasedOnNumber(game.getBoard(),1);
        move.setToCell(toCell);
        player.getPreviousMoves().add(move);
        System.out.println(player.getName() + " has started the game and is at Cell - "+toCell.getCellNumber());
    }

    public static void makeMove(Player player, SnakeAndLadderGame game){
        int dieThrow = dieController.generateDiceNumber(game.getDice());
        Move fromMove = player.getLastMove();
        Cell fromCell = fromMove.getToCell();
        int toCellNumber = dieThrow + fromCell.getCellNumber(), totalCells = game.getBoard().getNumberOfRows() * game.getBoard().getNumberOfColumns();
        if (toCellNumber > totalCells){
            System.out.println("It looks like you need a number less than or equal to +"+(totalCells - fromCell.getCellNumber()) +" but you have thrown a - "+ dieThrow);
            return;
        }
        Board board = game.getBoard();
        Cell toCell = getCellBasedOnNumber(board,toCellNumber);
        Move move = new Move();
        move.setFromCell(fromCell);
        move.setToCell(toCell);
        player.getPreviousMoves().add(move);
        System.out.println(player.getName() + " has moved from Cell - "+fromCell.getCellNumber()+" to Cell - "+toCell.getCellNumber());
    }

    static Cell getCellBasedOnNumber(Board board, int number){
        Cell [][] cells = board.getCells();
        for(int i = 0;i<cells.length;i++)
            for (int j = 0;j<cells[i].length;j++){
                if (cells[i][j].getCellNumber() == number)
                    return cells[i][j];
            }
        return null;
    }

    public static GameStart canPlayerStartTheGame(Player player, SnakeAndLadderGame game){
        CheckGameStartRequestDTO checkGameStartRequestDTO = new CheckGameStartRequestDTO();
        checkGameStartRequestDTO.setBoard(game.getBoard());
        checkGameStartRequestDTO.setDice(game.getDice());
        checkGameStartRequestDTO.setPlayer(player);
        GameStart gameStart = game.getGameStartStratergy().canPlayerStartTheGame(checkGameStartRequestDTO);
        return gameStart;
    }

    public static GameStatus checkIfGameIsCompleted(CheckGameWinnerRequestDTO requestDTO){
        SnakeAndLadderGame game = requestDTO.game;

        GameWinningStratergy gameWinningStratergy = game.getGameWinningStratergy();

        return gameWinningStratergy.checkIfGameWon(requestDTO);
    }

}
