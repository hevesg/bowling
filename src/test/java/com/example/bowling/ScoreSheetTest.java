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
        scoreSheet.register(1);
        scoreSheet.register(2);
        assertEquals(1, scoreSheet.getActiveIndex());
        assertEquals(10, scoreSheet.getActiveFrame().getStandingPins());
    }

    @Test
    void changesActualIndexAfterStrike() throws Exception {
        scoreSheet.register(10);
        assertEquals(1, scoreSheet.getActiveIndex());
        assertEquals(10, scoreSheet.getActiveFrame().getStandingPins());
    }

}
