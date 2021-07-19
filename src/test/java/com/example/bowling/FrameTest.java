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

    @BeforeEach
    void init() {
        frame = new Frame();
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
        assertEquals(Boolean.FALSE, frame.hit(0));
        assertEquals(List.of(0), frame.getAttempts());
        assertEquals(10, frame.getStandingPins());
        assertEquals(FrameType.NONE, frame.getType());
        assertEquals(0, frame.getLocalScore());
    }

    @Test
    void attemptWith5Pins() throws Exception {
        assertEquals(Boolean.FALSE, frame.hit(5));
        assertEquals(List.of(5), frame.getAttempts());
        assertEquals(5, frame.getStandingPins());
        assertEquals(FrameType.NONE, frame.getType());
        assertEquals(5, frame.getLocalScore());
    }

    @Test
    void attemptWithStrike() throws Exception {
        assertEquals(Boolean.TRUE, frame.hit(10));
        assertEquals(List.of(10), frame.getAttempts());
        assertEquals(0, frame.getStandingPins());
        assertEquals(FrameType.STRIKE, frame.getType());
        assertEquals(10, frame.getLocalScore());
    }

    @Test
    void attemptWithSpare() throws Exception {
        assertEquals(Boolean.FALSE, frame.hit(8));
        assertEquals(Boolean.TRUE, frame.hit(2));
        assertEquals(List.of(8, 2), frame.getAttempts());
        assertEquals(0, frame.getStandingPins());
        assertEquals(FrameType.SPARE, frame.getType());
        assertEquals(10, frame.getLocalScore());
    }

    @Test
    void attemptTwice() throws Exception {
        assertEquals(Boolean.FALSE, frame.hit(1));
        assertEquals(Boolean.TRUE, frame.hit(1));
        assertEquals(List.of(1, 1), frame.getAttempts());
        assertEquals(8, frame.getStandingPins());
        assertEquals(FrameType.OPEN, frame.getType());
        assertEquals(2, frame.getLocalScore());
    }

    @Test
    void attemptWithMoreHitsThanStanding() {
        assertThrows(Exception.class, () -> frame.hit(11));
    }

    @Test
    void attemptOnClosedFrame() throws Exception {
        frame.hit(1);
        frame.hit(1);
        assertThrows(Exception.class, () -> frame.hit(1));
    }
}
