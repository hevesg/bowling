package com.example.bowling;

import java.util.ArrayList;
import java.util.List;

public class ScoreSheet {

    private final List<Frame> frames;
    private Integer activeIndex;


    public ScoreSheet() {
        frames = new ArrayList<>();
        activeIndex = 0;
        for (int i = 0; i < 10; i++) {
            frames.add(new Frame());
        }
    }

    public Boolean hit(Integer pins) throws Exception {
        if (activeIndex >= 10) {
            throw new Exception("No more frames");
        }
        Boolean jumpFrame = frames.get(activeIndex).hit(pins);
        if (jumpFrame) {
            activeIndex++;
        }
        return jumpFrame;
    }

    public Integer getActiveIndex() {
        return activeIndex;
    }

    public Frame getActiveFrame() {
        return frames.get(activeIndex);
    }
}
