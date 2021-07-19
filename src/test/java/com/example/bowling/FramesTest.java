package com.example.bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class FramesTest {

    private Frames frames;

    @BeforeEach
    void init() {
        frames = new Frames();
    }

    @Test
    void initializedCorrectly() throws Exception {
        assertEquals(0, frames.getActiveIndex());
    }

    @Test
    void changesActualIndexAfterOpenFrame() throws Exception {
        assertEquals(Boolean.FALSE, frames.hit(1));
        assertEquals(Boolean.TRUE, frames.hit(2));
        assertEquals(1, frames.getActiveIndex());
        assertEquals(10, frames.getActiveFrame().getStandingPins());
    }

    @Test
    void changesActualIndexAfterStrike() throws Exception {
        assertEquals(Boolean.TRUE, frames.hit(10));
        assertEquals(1, frames.getActiveIndex());
        assertEquals(10, frames.getActiveFrame().getStandingPins());
    }

}
