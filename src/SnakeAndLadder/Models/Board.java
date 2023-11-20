package SnakeAndLadder.Models;

import java.util.List;

public class Board extends BaseClass{
    private int numberOfColumns, numberOfRows;
    private Cell [][] cells;
    private List<Snake> snakes;
    private List<Ladder> ladders;

    public List<Ladder> getLadders() {
        return ladders;
    }

    public void setLadders(List<Ladder> ladders) {
        this.ladders = ladders;
    }

    public List<Snake> getSnakes() {
        return snakes;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public void setNumberOfColumns(int numberOfColumns) {
        this.numberOfColumns = numberOfColumns;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public void setSnakes(List<Snake> snakes) {
        this.snakes = snakes;
    }
}
