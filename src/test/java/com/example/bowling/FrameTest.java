package com.example.bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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
        assertEquals(0, frame.getAttempts());
        assertEquals(10, frame.getStandingPins());
    }

    @Test
    void attemptWithNoPins() throws Exception {
        Boolean attempt = frame.hit(0);
        assertEquals(Boolean.FALSE, attempt);
        assertEquals(1, frame.getAttempts());
        assertEquals(10, frame.getStandingPins());
    }

    @Test
    void attemptWith5Pins() throws Exception {
        Boolean attempt = frame.hit(5);
        assertEquals(Boolean.FALSE, attempt);
        assertEquals(1, frame.getAttempts());
        assertEquals(5, frame.getStandingPins());
    }

    @Test
    void attemptWithStrike() throws Exception {
        Boolean attempt = frame.hit(10);
        assertEquals(Boolean.TRUE, attempt);
        assertEquals(1, frame.getAttempts());
        assertEquals(0, frame.getStandingPins());
    }

    @Test
    void attemptTwice() throws Exception {
        Boolean firstAttempt = frame.hit(1);
        Boolean secondAttempt = frame.hit(1);
        assertEquals(Boolean.FALSE, firstAttempt);
        assertEquals(Boolean.TRUE, secondAttempt);
        assertEquals(2, frame.getAttempts());
        assertEquals(8, frame.getStandingPins());
    }

    @Test
    void attemptWithMoreHitsThanStanding() {
        assertThrows(Exception.class, () -> frame.hit(11));
    }
}
