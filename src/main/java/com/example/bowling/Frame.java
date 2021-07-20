package com.example.bowling;

import java.util.ArrayList;
import java.util.List;

public abstract class Frame {
    protected Integer standingPins;
    protected final List<Integer> attempts;
    protected FrameType type;
    protected final Frame previousFrame;
    protected Integer localScore;
    protected Integer totalScore;

    protected Frame(Frame previous) {
        previousFrame = previous;
        attempts = new ArrayList<>();
        standingPins = 10;
        localScore = 0;
        type = FrameType.NONE;
        totalScore = 0;
    }

    protected abstract void finalizeScore(Frame next);
    public abstract Boolean register(Integer pins) throws Exception;

    public Boolean hasPreviousFrame() {
        return previousFrame != null;
    }

    public Integer getStandingPins() {
        return standingPins;
    }

    public List<Integer> getAttempts() {
        return attempts;
    }

    public FrameType getType() {
        return type;
    }

    public Integer getLocalScore() {
        return localScore;
    }

    public Boolean isStrike() {
        return type == FrameType.STRIKE;
    }

    public Boolean isSpare() {
        return type == FrameType.SPARE;
    }

    public Boolean isOpen() {
        return type == FrameType.OPEN;
    }

    public Integer getTotalScore() {
        return totalScore;
    }
}
