package com;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.example.joueur.*;
import com.example.strategie.*;

class SondeurRepentantTest {
    ArrayList<Coup> historiqueJ1 = new ArrayList<>();
    
    ArrayList<Coup> historiqueJ2 = new ArrayList<>(); 
    
    @Test
    void testSondeurRepentant() {
        Strategie a = new SondeurRepentant();
        
        historiqueJ1.add(Coup.TRAHIR);
        historiqueJ2.add(Coup.TRAHIR);
        assertEquals(Coup.COOPERER, a.prochainCoup(historiqueJ1, historiqueJ2));
    }
}