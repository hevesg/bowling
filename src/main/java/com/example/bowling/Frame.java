package com.example.bowling;

import java.util.ArrayList;
import java.util.List;

public class Frame {
    private Integer standingPins;
    private final List<Integer> attempts;
    private FrameType type;

    public Frame() {
        standingPins = 10;
        attempts = new ArrayList<>();
        type = FrameType.NONE;
    }

    public Boolean hit(Integer pins) throws Exception {
        if (pins > standingPins) {
            throw new Exception("Too many pins are hit");
        } else if (type != FrameType.NONE) {
            throw new Exception("Frame is closed");
        }

        attempts.add(pins);
        standingPins -= pins;

        if ((standingPins == 0) || (attempts.size() == 2)) {
            finalizeCard();
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
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
        return 10 - standingPins;
    }
}
