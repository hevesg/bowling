package com.example.bowling;

public class Frame {
    private Integer standingPins;
    private Integer attempts;

    public Frame() {
        standingPins = 10;
        attempts = 0;
    }

    public Boolean hit(Integer pins) throws Exception {
        if (pins > standingPins) {
            throw new Exception("Too many pins are hit");
        }
        attempts ++;
        standingPins -= pins;
        if ((standingPins == 0) || (attempts > 1)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Integer getStandingPins() {
        return standingPins;
    }

    public Integer getAttempts() {
        return attempts;
    }
}
