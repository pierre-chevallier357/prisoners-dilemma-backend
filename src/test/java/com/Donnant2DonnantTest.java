package com;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.example.joueur.*;
import com.example.strategie.*;

class Donnant2DonnantTest {
    ArrayList<Coup> historiqueJ1 = new ArrayList<>();
    
    ArrayList<Coup> historiqueJ2 = new ArrayList<>(); 
    @Test
    void testDonnant2Donnant() {
        Strategie a = new Donnant2Donnant();
	    Coup coup  = Coup.COOPERER;
        assertEquals(coup.getClass().getSimpleName(), a.prochainCoup(historiqueJ1, historiqueJ2).getClass().getSimpleName());
    }
}
