package com.example.bowling;

public class LastFrame extends Frame {

    public LastFrame(Frame previous) {
        super(previous);
    }

    @Override
    protected void finalizeScore(Frame next) { }

    @Override
    public Boolean register(Integer pins) throws Exception {
        checkIfValidHit(pins);

        attempts.add(pins);
        standingPins -= pins;
        localScore += pins;

        if (attempts.size() < 3 && standingPins == 0) {
            standingPins = 10;
            return Boolean.FALSE;
        } else if (attempts.size() < 2) {
            return Boolean.FALSE;
        } else {
            previousFrame.finalizeScore(this);
            type = FrameType.LAST;
            return Boolean.TRUE;
        }
    }
}
