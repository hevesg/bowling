package com.example.bowling;

class LastFrame extends Frame {

    LastFrame(Frame previous) {
        super(previous);
        maxAttempts = 3;
    }

    @Override
    protected void finalizeScore(Frame next) { }

    @Override
    public Boolean registerHit(Integer pins) throws Exception {
        validateHit(pins);

        attempts.add(pins);
        standingPins -= pins;

        if (attempts.size() < maxAttempts && !hasStandingPins()) {
            standingPins = 10;
            return Boolean.FALSE;
        } else if (attempts.size() < 2) {
            return Boolean.FALSE;
        } else {
            previousFrame.finalizeScore(this);
            score = getLocalScore();
            type = FrameType.LAST;
            return Boolean.TRUE;
        }
    }
}
