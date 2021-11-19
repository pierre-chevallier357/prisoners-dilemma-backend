package com;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.example.joueur.*;
import com.example.strategie.*;

class ToujoursCoopererTest {
    ArrayList<Coup> historiqueJ1 = new ArrayList<>();
    
    ArrayList<Coup> historiqueJ2 = new ArrayList<>(); 
    
    @Test
    void testToujoursCooperer() {
        Strategie a = new ToujoursCooperer();
        assertEquals(Coup.COOPERER, a.prochainCoup(historiqueJ1, historiqueJ2));
    }
}