package SnakeAndLadder.Models;

public class Snake extends BaseClass{
    private Cell snakeHead, snakeTail;

    public Cell getSnakeHead() {
        return snakeHead;
    }

    public void setSnakeHead(Cell snakeHead) {
        this.snakeHead = snakeHead;
    }

    public Cell getSnakeTail() {
        return snakeTail;
    }

    public void setSnakeTail(Cell snakeTail) {
        this.snakeTail = snakeTail;
    }
}
