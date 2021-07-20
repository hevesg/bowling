package com.example.bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class FrameTest {
    private Frame frame;
    private Frame nextFrame;

    @BeforeEach
    void init() {
        frame = new Frame(null);
        nextFrame = new Frame(frame);
    }

    @Test
    void initializedCorrectly() {
        assertEquals(List.of(), frame.getAttempts());
        assertEquals(10, frame.getStandingPins());
        assertEquals(FrameType.NONE, frame.getType());
        assertEquals(0, frame.getLocalScore());
    }

    @Test
    void attemptWithNoPins() throws Exception {
        assertEquals(Boolean.FALSE, frame.register(0));
        assertEquals(List.of(0), frame.getAttempts());
        assertEquals(10, frame.getStandingPins());
        assertEquals(FrameType.NONE, frame.getType());
        assertEquals(0, frame.getLocalScore());
    }

    @Test
    void attemptWith5Pins() throws Exception {
        assertEquals(Boolean.FALSE, frame.register(5));
        assertEquals(List.of(5), frame.getAttempts());
        assertEquals(5, frame.getStandingPins());
        assertEquals(FrameType.NONE, frame.getType());
        assertEquals(0, frame.getLocalScore());
    }

    @Test
    void attemptWithStrike() throws Exception {
        assertEquals(Boolean.TRUE, frame.register(10));
        assertEquals(List.of(10), frame.getAttempts());
        assertEquals(0, frame.getStandingPins());
        assertEquals(FrameType.STRIKE, frame.getType());
        assertEquals(0, frame.getLocalScore());
    }

    @Test
    void attemptWithSpare() throws Exception {
        assertEquals(Boolean.FALSE, frame.register(8));
        assertEquals(Boolean.TRUE, frame.register(2));
        assertEquals(List.of(8, 2), frame.getAttempts());
        assertEquals(0, frame.getStandingPins());
        assertEquals(FrameType.SPARE, frame.getType());
        assertEquals(0, frame.getLocalScore());
    }

    @Test
    void attemptTwice() throws Exception {
        assertEquals(Boolean.FALSE, frame.register(1));
        assertEquals(Boolean.TRUE, frame.register(1));
        assertEquals(List.of(1, 1), frame.getAttempts());
        assertEquals(8, frame.getStandingPins());
        assertEquals(FrameType.OPEN, frame.getType());
        assertEquals(2, frame.getLocalScore());
    }

    @Test
    void attemptWithMoreHitsThanStanding() {
        assertThrows(Exception.class, () -> frame.register(11));
    }

    @Test
    void attemptOnClosedFrame() throws Exception {
        frame.register(1);
        frame.register(1);
        assertThrows(Exception.class, () -> frame.register(1));
    }

    @Test
    void updatePreviousWithStrike() throws Exception {
        frame.register(10);
        nextFrame.register(5);
        nextFrame.register(4);
        assertEquals(19, frame.getLocalScore());
    }

    @Test
    void updatePreviousWithSpare() throws Exception {
        frame.register(5);
        frame.register(5);
        nextFrame.register(5);
        nextFrame.register(4);
        assertEquals(15, frame.getLocalScore());
    }

    @Test
    void noUpdatePreviousWithOpen() throws Exception {
        frame.register(5);
        frame.register(0);
        nextFrame.register(5);
        nextFrame.register(4);
        assertEquals(5, frame.getLocalScore());
        assertEquals(9, nextFrame.getLocalScore());
    }
}
