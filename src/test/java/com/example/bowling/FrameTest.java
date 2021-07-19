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
    }

    @Test
    void attemptWithNoPins() throws Exception {
        Boolean attempt = frame.hit(0);
        assertEquals(Boolean.FALSE, attempt);
        assertEquals(List.of(0), frame.getAttempts());
        assertEquals(10, frame.getStandingPins());
        assertEquals(FrameType.NONE, frame.getType());
    }

    @Test
    void attemptWith5Pins() throws Exception {
        Boolean attempt = frame.hit(5);
        assertEquals(Boolean.FALSE, attempt);
        assertEquals(List.of(5), frame.getAttempts());
        assertEquals(5, frame.getStandingPins());
        assertEquals(FrameType.NONE, frame.getType());
    }

    @Test
    void attemptWithStrike() throws Exception {
        Boolean attempt = frame.hit(10);
        assertEquals(Boolean.TRUE, attempt);
        assertEquals(List.of(10), frame.getAttempts());
        assertEquals(0, frame.getStandingPins());
        assertEquals(FrameType.STRIKE, frame.getType());
    }

    @Test
    void attemptWithSpare() throws Exception {
        assertEquals(Boolean.FALSE, frame.hit(8));
        assertEquals(Boolean.TRUE, frame.hit(2));
        assertEquals(List.of(8, 2), frame.getAttempts());
        assertEquals(0, frame.getStandingPins());
        assertEquals(FrameType.SPARE, frame.getType());
    }

    @Test
    void attemptTwice() throws Exception {
        Boolean firstAttempt = frame.hit(1);
        Boolean secondAttempt = frame.hit(1);
        assertEquals(Boolean.FALSE, firstAttempt);
        assertEquals(Boolean.TRUE, secondAttempt);
        assertEquals(List.of(1, 1), frame.getAttempts());
        assertEquals(8, frame.getStandingPins());
        assertEquals(FrameType.OPEN, frame.getType());
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
