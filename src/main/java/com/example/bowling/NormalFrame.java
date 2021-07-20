package com.example.bowling;

public class NormalFrame extends Frame {

    public NormalFrame(Frame previous) {
        super(previous);
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

    @Override
    protected void finalizeScore(Frame next) {
        if (isStrike() || isSpare()) {
            if (isStrike()) {
                localScore = 10 + 10 - next.getStandingPins();
            } else if (isSpare()) {
                localScore = 10 + next.getAttempts().get(0);
            }
            if (hasPreviousFrame()) {
                totalScore = previousFrame.getTotalScore() + localScore;
            }
        }
    }

    @Override
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
                if (hasPreviousFrame()) {
                    totalScore = previousFrame.getTotalScore() + localScore;
                } else {
                    totalScore = localScore;
                }
            }
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
}
