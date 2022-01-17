package com;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import com.example.joueur.Coup;
import com.example.strategie.AdaptStrategie;

import strategie2.Choix;

import org.junit.jupiter.api.Test;

public class AdaptStrategieTest {
    
    ArrayList<Coup> historiqueJ1 = new ArrayList<>();
    ArrayList<Coup> historiqueJ2 = new ArrayList<>();

    ArrayList<Choix> historiqueChoixJ1 = new ArrayList<>();
    @Test
    void testAdaptCoup() {
        AdaptStrategie adap = new AdaptStrategie(13);
        historiqueJ1.add(Coup.COOPERER);
        assertEquals(Coup.COOPERER, adap.prochainCoup(historiqueJ2, historiqueJ1));
        historiqueJ1.add(Coup.TRAHIR);
        assertEquals(Coup.TRAHIR, adap.prochainCoup(historiqueJ2, historiqueJ1));
        AdaptStrategie adap2 = new AdaptStrategie(14);
        assertEquals(Coup.TRAHIR, adap2.prochainCoup(historiqueJ2, historiqueJ1));
    }
    
    @Test
    void testlistAdapter() {
    	historiqueJ1.add(Coup.COOPERER);
    	historiqueChoixJ1 = AdaptStrategie.listAdapter(historiqueJ1);
    	assertEquals(Choix.COOPERER, historiqueChoixJ1.get(historiqueChoixJ1.size()-1));
    	historiqueJ1.clear();
    	historiqueJ1.add(Coup.TRAHIR);
    	historiqueChoixJ1 = AdaptStrategie.listAdapter(historiqueJ1);
    	assertEquals(Choix.TRAHIR, historiqueChoixJ1.get(historiqueChoixJ1.size()-1));
    }
    
}
