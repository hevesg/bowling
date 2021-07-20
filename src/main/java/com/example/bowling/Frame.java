package com.example.bowling;

import java.util.ArrayList;
import java.util.List;

abstract class Frame {
    protected Integer standingPins;
    protected final List<Integer> attempts;
    protected FrameType type;
    protected final Frame previousFrame;
    protected Integer score;
    protected Integer maxAttempts;

    protected Frame(Frame previous) {
        previousFrame = previous;
        attempts = new ArrayList<>();
        standingPins = 10;
        type = FrameType.NONE;
        score = 0;
    }

    protected abstract void finalizeScore(Frame next);

    abstract Boolean registerHit(Integer pins) throws Exception;

    protected void validateHit(Integer pins) throws Exception {
        if (pins > standingPins) {
            throw new Exception("Too many pins are hit");
        } else if (type != FrameType.NONE) {
            throw new Exception("Frame is closed");
        }
    }

    protected Boolean hasPreviousFrame() {
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

    protected Integer getLocalScore() {
        return attempts.stream().reduce(0, Integer::sum);
    }

    public Integer getScore() {
        return score;
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

    public Boolean hasStandingPins() {
        return standingPins > 0;
    }

    public Integer getTotalScore() {
        if (hasPreviousFrame()) {
            return previousFrame.getTotalScore() + score;
        } else {
            return score;
        }
    }

    @Override
    public String toString() {
        return getTotalScore() + ":\t" + String.join(" ", attempts.stream().map(Object::toString).toList());
    }
}
