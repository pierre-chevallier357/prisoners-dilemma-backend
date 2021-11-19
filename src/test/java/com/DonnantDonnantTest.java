package com;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.example.joueur.*;
import com.example.strategie.*;

class DonnantDonnantTest {
    ArrayList<Coup> historiqueJ1 = new ArrayList<>();
    
    ArrayList<Coup> historiqueJ2 = new ArrayList<>(); 
    @Test
    void testDonnantDonnant() {
        Strategie a = new DonnantDonnant();
        historiqueJ2.add(Coup.COOPERER);
        assertEquals(Coup.COOPERER, a.prochainCoup(historiqueJ1, historiqueJ2));
    }
}
