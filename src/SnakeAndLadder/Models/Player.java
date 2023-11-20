package SnakeAndLadder.Models;

import java.util.List;

public class Player extends BaseClass{
    private String name;
    private List<Move> previousMoves;
    private List<Integer> dieThrows;

    public List<Integer> getDieThrows() {
        return dieThrows;
    }

    public void setDieThrows(List<Integer> dieThrows) {
        this.dieThrows = dieThrows;
    }

    public List<Move> getPreviousMoves() {
        return previousMoves;
    }

    public void setPreviousMoves(List<Move> previousMoves) {
        this.previousMoves = previousMoves;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Move getLastMove(){
        if (previousMoves.size() > 0)
            return previousMoves.get(previousMoves.size() - 1);
        return null;
    }
}
