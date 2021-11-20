package com;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.example.joueur.*;
import com.example.strategie.*;

class RancunierTest {
    ArrayList<Coup> historiqueJ1 = new ArrayList<>();
    
    ArrayList<Coup> historiqueJ2 = new ArrayList<>(); 
    
    @Test
    void testRancunier() {
        Strategie a = new Rancunier();
        assertEquals(Coup.COOPERER, a.prochainCoup(historiqueJ1, historiqueJ2));
        historiqueJ2.add(Coup.COOPERER);
        historiqueJ2.add(Coup.TRAHIR);
        assertEquals(Coup.TRAHIR, a.prochainCoup(historiqueJ1, historiqueJ2));
    }
}
