package com;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.joueur.*;
import com.example.partiedejeux.*;

public class HistoriqueTest {

    // Test HistoriqueJoueur.java

    Joueur j = new Joueur();
    HistoriqueJoueur hj = new HistoriqueJoueur();


    @Test
    public void testLastCoup() {
        j.setCoup(Coup.COOPERER);
        j.setResultat(Resultat.P);
        hj.addCoupRes(j);
        assertEquals(Coup.COOPERER, hj.getLastCoup());
    }

    @Test
    public void testCalculRes() {
        j.setCoup(Coup.COOPERER);
        j.setResultat(Resultat.P);
        hj.addCoupRes(j);
        assertEquals(1, hj.calculPoint());
    }
}