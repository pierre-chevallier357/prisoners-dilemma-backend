package com;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.joueur.*;
import com.example.strategie.*;

class StrategieTest {
    @Test
    void testStrategie() {
	    Coup coup  = Coup.COOPERER;

        assertEquals(coup.getClass().getSimpleName(), Strategie.coupAleatoire(0.5).getClass().getSimpleName());
    }
}