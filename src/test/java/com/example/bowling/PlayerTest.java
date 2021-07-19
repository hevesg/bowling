package com.example.bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PlayerTest {
    private Player ringo;

    @BeforeEach
    void init() {
        ringo = new Player();
    }

    @Test
    void playerHits() throws Exception {
        ringo.hit(0);
        assertEquals(0, ringo.getTotalScore());
    }

    @Test
    void providedExample() throws Exception {
        ringo.hit(1);
        ringo.hit(4);
        ringo.hit(4);
        ringo.hit(5);
        ringo.hit(6);
        ringo.hit(4);
        ringo.hit(5);
        ringo.hit(5);
        ringo.hit(10);
        ringo.hit(0);
        ringo.hit(1);
        ringo.hit(7);
        ringo.hit(3);
        ringo.hit(6);
        ringo.hit(4);
        ringo.hit(10);
        ringo.hit(2);
        ringo.hit(8);
        // ringo.hit(6);
        assertEquals(133, ringo.getTotalScore());
    }
}
