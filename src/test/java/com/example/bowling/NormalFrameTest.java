package com.example.bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class NormalFrameTest {
    private NormalFrame frame;
    private NormalFrame nextFrame;

    @BeforeEach
    void init() {
        frame = new NormalFrame(null);
        nextFrame = new NormalFrame(frame);
    }

    @Test
    void initializedCorrectly() {
        assertEquals(List.of(), frame.getAttempts());
        assertEquals(10, frame.getStandingPins());
        assertEquals(FrameType.NONE, frame.getType());
        assertEquals(0, frame.getLocalScore());
        assertEquals(0, frame.getScore());
    }

    @Test
    void attemptWithNoPins() throws Exception {
        assertEquals(Boolean.FALSE, frame.registerHit(0));
        assertEquals(List.of(0), frame.getAttempts());
        assertEquals(10, frame.getStandingPins());
        assertEquals(FrameType.NONE, frame.getType());
        assertEquals(0, frame.getLocalScore());
        assertEquals(0, frame.getScore());
    }

    @Test
    void attemptWith5Pins() throws Exception {
        assertEquals(Boolean.FALSE, frame.registerHit(5));
        assertEquals(List.of(5), frame.getAttempts());
        assertEquals(5, frame.getStandingPins());
        assertEquals(FrameType.NONE, frame.getType());
        assertEquals(5, frame.getLocalScore());
        assertEquals(0, frame.getScore());
    }

    @Test
    void attemptWithStrike() throws Exception {
        assertEquals(Boolean.TRUE, frame.registerHit(10));
        assertEquals(List.of(10), frame.getAttempts());
        assertEquals(0, frame.getStandingPins());
        assertEquals(FrameType.STRIKE, frame.getType());
        assertEquals(10, frame.getLocalScore());
        assertEquals(0, frame.getScore());
    }

    @Test
    void attemptWithSpare() throws Exception {
        assertEquals(Boolean.FALSE, frame.registerHit(8));
        assertEquals(Boolean.TRUE, frame.registerHit(2));
        assertEquals(List.of(8, 2), frame.getAttempts());
        assertEquals(0, frame.getStandingPins());
        assertEquals(FrameType.SPARE, frame.getType());
        assertEquals(10, frame.getLocalScore());
        assertEquals(0, frame.getScore());
    }

    @Test
    void attemptTwice() throws Exception {
        assertEquals(Boolean.FALSE, frame.registerHit(1));
        assertEquals(Boolean.TRUE, frame.registerHit(1));
        assertEquals(List.of(1, 1), frame.getAttempts());
        assertEquals(8, frame.getStandingPins());
        assertEquals(FrameType.OPEN, frame.getType());
        assertEquals(2, frame.getLocalScore());
    }

    @Test
    void attemptWithMoreHitsThanStanding() {
        assertThrows(Exception.class, () -> frame.registerHit(11));
    }

    @Test
    void attemptOnClosedFrame() throws Exception {
        frame.registerHit(1);
        frame.registerHit(1);
        assertThrows(Exception.class, () -> frame.registerHit(1));
    }

    @Test
    void updatePreviousWithStrike() throws Exception {
        frame.registerHit(10);
        nextFrame.registerHit(5);
        nextFrame.registerHit(4);
        assertEquals(10, frame.getLocalScore());
        assertEquals(19, frame.getScore());
    }

    @Test
    void updatePreviousWithSpare() throws Exception {
        frame.registerHit(5);
        frame.registerHit(5);
        nextFrame.registerHit(5);
        nextFrame.registerHit(4);
        assertEquals(10, frame.getLocalScore());
        assertEquals(15, frame.getScore());
    }

    @Test
    void noUpdatePreviousWithOpen() throws Exception {
        frame.registerHit(5);
        frame.registerHit(0);
        nextFrame.registerHit(5);
        nextFrame.registerHit(4);
        assertEquals(5, frame.getScore());
        assertEquals(9, nextFrame.getScore());
    }
}
