package com.example.bowling;

import java.util.ArrayList;
import java.util.List;

public class Frames {

    private final List<Frame> list;
    private Integer activeIndex;

    public Frames() {
        list = new ArrayList<>();
        activeIndex = 0;
        for (int i = 0; i < 10; i++) {
            list.add(new Frame());
        }
    }

    public Boolean hit(Integer pins) throws Exception {
        if (activeIndex >= 10) {
            throw new Exception("No more frames");
        }
        Boolean jumpFrame = list.get(activeIndex).hit(pins);
        if (jumpFrame) {
            activeIndex++;
        }
        return jumpFrame;
    }

    public Integer getActiveIndex() {
        return activeIndex;
    }

    public Frame getActiveFrame() {
        return list.get(activeIndex);
    }
}
