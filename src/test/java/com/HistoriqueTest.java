package com;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.example.joueur.*;
import com.example.partiedejeux.*;

class HistoriqueTest {

    // Test HistoriqueJoueur.java

    Joueur j = new Joueur();
    HistoriqueJoueur hj = new HistoriqueJoueur();


    @Test
    void testLastCoup() {
        j.setCoup(Coup.COOPERER);
        j.setResultat(Resultat.P);
        hj.addCoupRes(j);
        assertEquals(Coup.COOPERER, hj.getLastCoup());
    }

    @Test
    void testCalculPoint() {
        j.setCoup(Coup.COOPERER);
        j.setResultat(Resultat.P);
        hj.addCoupRes(j);
        assertEquals(1, hj.calculPoint());
    }

    @Test
    void testGetResultat() {
        j.setCoup(Coup.COOPERER);
        j.setResultat(Resultat.P);
        hj.addCoupRes(j);
        assertEquals(Resultat.P, hj.getResultat(0));
    }

    @Test
    void testGetCoup() {
        j.setCoup(Coup.COOPERER);
        j.setResultat(Resultat.P);
        hj.addCoupRes(j);
        assertEquals(Coup.COOPERER, hj.getCoup(0));
    }

    @Test
    void testGetListeCoup() {
        
	    ArrayList<Coup> coupList = new ArrayList<>();

        assertEquals(coupList.getClass().getSimpleName(), hj.getListCoup().getClass().getSimpleName());
    }

    @Test
    void testGetListeRes() {
        
	    ArrayList<Resultat> resultatList = new ArrayList<>();

        assertEquals(resultatList.getClass().getSimpleName(), hj.getListResultat().getClass().getSimpleName());
    }
}