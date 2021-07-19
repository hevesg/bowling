package com.example.bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ScoreSheetTest {

    private ScoreSheet scoreSheet;

    @BeforeEach
    void init() {
        scoreSheet = new ScoreSheet();
    }

    @Test
    void initializedCorrectly() {
        assertEquals(0, scoreSheet.getActiveIndex());
    }

    @Test
    void changesActualIndexAfterOpenFrame() throws Exception {
        assertEquals(Boolean.FALSE, scoreSheet.hit(1));
        assertEquals(Boolean.TRUE, scoreSheet.hit(2));
        assertEquals(1, scoreSheet.getActiveIndex());
        assertEquals(10, scoreSheet.getActiveFrame().getStandingPins());
    }

    @Test
    void changesActualIndexAfterStrike() throws Exception {
        assertEquals(Boolean.TRUE, scoreSheet.hit(10));
        assertEquals(1, scoreSheet.getActiveIndex());
        assertEquals(10, scoreSheet.getActiveFrame().getStandingPins());
    }

}
