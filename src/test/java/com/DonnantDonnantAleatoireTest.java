package com;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.example.joueur.*;
import com.example.strategie.*;

class DonnantDonnantAleatoireTest {
    ArrayList<Coup> historiqueJ1 = new ArrayList<>();
    
    ArrayList<Coup> historiqueJ2 = new ArrayList<>(); 
    @Test
    void testDonnantDonnantAleatoire() {
        Strategie a = new DonnantDonnantAleatoire();
	    Coup coup  = Coup.COOPERER;
        assertEquals(coup.getClass().getSimpleName(), a.prochainCoup(historiqueJ1, historiqueJ2).getClass().getSimpleName());
        historiqueJ2.add(coup);
        assertEquals(coup.getClass().getSimpleName(), a.prochainCoup(historiqueJ1, historiqueJ2).getClass().getSimpleName());
    }
}
