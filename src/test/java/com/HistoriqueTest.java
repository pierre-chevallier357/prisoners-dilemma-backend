package com;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.joueur.*;
import com.example.partiedejeux.*;

public class HistoriqueTest {

    // Test HistoriqueJoueur.java

    @Test
    public void testLastCoup() {
        Joueur j = new Joueur();
        j.setCoup(Coup.COOPERER);
        j.setResultat(Resultat.P);
        HistoriqueJoueur hj = new HistoriqueJoueur();
        hj.addCoupRes(j);
        assertEquals(Coup.COOPERER, hj.getLastCoup());
    }

    @Test
    public void testCalculRes() {
        Joueur j = new Joueur();
        j.setCoup(Coup.COOPERER);
        j.setResultat(Resultat.P);
        HistoriqueJoueur hj = new HistoriqueJoueur();
        hj.addCoupRes(j);
        assertEquals(1, hj.calculPoint());
    }
}