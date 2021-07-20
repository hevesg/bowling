package com.example.bowling;

public class NormalFrame extends Frame {

    public NormalFrame(Frame previous) {
        super(previous);
        maxAttempts = 2;
    }

    private void closeFrame() {
        if (standingPins == 0 && attempts.size() == 1) {
            type = FrameType.STRIKE;
        } else if (standingPins == 0 && attempts.size() == maxAttempts) {
            type = FrameType.SPARE;
        } else if (standingPins > 0 && attempts.size() == maxAttempts) {
            type = FrameType.OPEN;
        }

        if (hasPreviousFrame()) {
            previousFrame.finalizeScore(this);
        }
        if (isOpen()) {
            score = getLocalScore();
        }
    }

    @Override
    protected void finalizeScore(Frame next) {
        if (isStrike() || isSpare()) {
            if (isStrike()) {
                if (next.attempts.size() < 3) {
                    score = getLocalScore() + next.getLocalScore();
                } else {
                    score = getLocalScore() + next.getAttempts().get(0) + next.getAttempts().get(1);
                }
            } else if (isSpare()) {
                score = getLocalScore() + next.getAttempts().get(0);
            }
        }
    }

    @Override
    public Boolean register(Integer pins) throws Exception {
        validateHit(pins);

        attempts.add(pins);
        standingPins -= pins;

        if ((standingPins == 0) || (attempts.size() == maxAttempts)) {
            closeFrame();

            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
}
