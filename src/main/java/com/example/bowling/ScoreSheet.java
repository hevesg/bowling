package com.example.bowling;

import java.util.ArrayList;
import java.util.List;

class ScoreSheet {

    private final List<Frame> frames;
    private Integer activeIndex;

    ScoreSheet() {
        frames = new ArrayList<>();
        activeIndex = 0;
        build();
    }

    private void build() {
        frames.add(new NormalFrame(null));
        for (int i = 0; i < 8; i++) {
            frames.add(new NormalFrame(frames.get(i)));
        }
        frames.add(new LastFrame(frames.get(8)));
    }

    void registerHit(Integer pins) throws Exception {
        if (activeIndex >= 10) {
            throw new Exception("No more frames");
        }
        Boolean jumpFrame = getActiveFrame().registerHit(pins);
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
        return frames.stream().reduce(0, (subtotal, frame) -> subtotal + frame.getScore(), Integer::sum);
    }

    @Override
    public String toString() {
        return String.join("\n", frames.stream().map(Object::toString).toList());
    }
}
