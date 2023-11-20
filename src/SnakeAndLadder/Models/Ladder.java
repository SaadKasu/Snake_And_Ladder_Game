package SnakeAndLadder.Models;

public class Ladder extends BaseClass{
    private Cell ladderStart, ladderEnd;

    public Cell getLadderStart() {
        return ladderStart;
    }

    public void setLadderStart(Cell ladderStart) {
        this.ladderStart = ladderStart;
    }

    public Cell getLadderEnd() {
        return ladderEnd;
    }

    public void setLadderEnd(Cell ladderEnd) {
        this.ladderEnd = ladderEnd;
    }
}
