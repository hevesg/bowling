package com.example.bowling;

import java.util.ArrayList;
import java.util.List;

public class Frame {
    private Integer standingPins;
    private final List<Integer> attempts;
    private FrameType type;
    private final Frame previousFrame;
    private Integer localScore;

    public Frame(Frame previous) {
        previousFrame = previous;
        standingPins = 10;
        attempts = new ArrayList<>();
        localScore = 0;
        type = FrameType.NONE;
    }

    public Boolean register(Integer pins) throws Exception {
        if (pins > standingPins) {
            throw new Exception("Too many pins are hit");
        } else if (type != FrameType.NONE) {
            throw new Exception("Frame is closed");
        }

        attempts.add(pins);
        standingPins -= pins;

        if ((standingPins == 0) || (attempts.size() == 2)) {
            finalizeCard();
            if (hasPreviousFrame()) {
                previousFrame.finalizeScore(this);
            }
            if (isOpen()) {
                localScore = attempts.get(0) + attempts.get(1);
            }
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    private void finalizeScore(Frame next) {
        if (isStrike()) {
            localScore = 10 + 10 - next.getStandingPins();
        } else if (isSpare()) {
            localScore = 10 + next.getAttempts().get(0);
        }
    }

    private void finalizeCard() {
        if (standingPins == 0 && attempts.size() == 1) {
            type = FrameType.STRIKE;
        } else if (standingPins == 0 && attempts.size() == 2) {
            type = FrameType.SPARE;
        } else if (standingPins > 0 && attempts.size() == 2) {
            type = FrameType.OPEN;
        }
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

}
