package com.example.bowling;

import java.util.ArrayList;
import java.util.List;

public class ScoreSheet {

    private final List<Frame> frames;
    private Integer activeIndex;

    public ScoreSheet() {
        frames = new ArrayList<>();
        activeIndex = 0;
        frames.add(new Frame(null));
        for (int i = 0; i < 9; i++) {
            frames.add(new Frame(frames.get(i)));
        }
    }

    public void register(Integer pins) throws Exception {
        if (activeIndex >= 10) {
            throw new Exception("No more frames");
        }
        Boolean jumpFrame = getActiveFrame().register(pins);
        if (jumpFrame) {
            activeIndex++;
        }
    }

    public Integer getActiveIndex() {
        return activeIndex;
    }

    public Frame getActiveFrame() {
        return frames.get(activeIndex);
    }

    public Integer getTotalScore() {
        return frames.stream().reduce(0, (subtotal, frame) -> subtotal + frame.getLocalScore(), Integer::sum);
    }
}
