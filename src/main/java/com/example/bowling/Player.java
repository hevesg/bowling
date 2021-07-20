package com.example.bowling;

public class Player {
    private final ScoreSheet scoreSheet;

    public Player() {
        scoreSheet = new ScoreSheet();
    }

    public void hit(Integer pins) throws Exception {
        scoreSheet.registerHit(pins);
    }

    public ScoreSheet getScoreSheet() {
        return scoreSheet;
    }

    public Integer getTotalScore() {
        return scoreSheet.getTotalScore();
    }
}
