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
                localScore += next.getLocalScore();
            } else if (isSpare()) {
                localScore += next.getAttempts().get(0);
            }
        }
    }

    @Override
    public Boolean register(Integer pins) throws Exception {
        checkIfValidHit(pins);

        attempts.add(pins);
        standingPins -= pins;
        localScore += pins;

        if ((standingPins == 0) || (attempts.size() == 2)) {
            finalizeCard();
            if (hasPreviousFrame()) {
                previousFrame.finalizeScore(this);
            }
            if (isOpen()) {
                score = localScore;
            }
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
}
