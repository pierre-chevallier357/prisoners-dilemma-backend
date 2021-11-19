package com;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.example.joueur.*;
import com.example.strategie.*;

class ToujoursTrahirTest {
    ArrayList<Coup> historiqueJ1 = new ArrayList<>();
    
    ArrayList<Coup> historiqueJ2 = new ArrayList<>(); 
    
    @Test
    void testToujoursTrahir() {
        Strategie a = new ToujoursTrahir();
        assertEquals(Coup.TRAHIR, a.prochainCoup(historiqueJ1, historiqueJ2));
    }
}