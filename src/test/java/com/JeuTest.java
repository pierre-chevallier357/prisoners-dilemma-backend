package com;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.example.joueur.*;
import com.example.partiedejeux.*;

class JeuTest {
    Joueur j1 = new Joueur();
    Joueur j2 = new Joueur();
    Jeu jeu = new Jeu();

    HistoriqueJoueur h1 = new HistoriqueJoueur();
    HistoriqueJoueur h2 = new HistoriqueJoueur();

    @Test
    void getDernierCoupAdvTest(){
        j1.setId(1);
        j1.setCoup(Coup.COOPERER);
        j1.setResultat(Resultat.P);
        j2.setId(2);
        jeu.setJoueur1(j1);
        
        j2.setCoup(Coup.TRAHIR);
        j2.setResultat(Resultat.C);

        jeu.setJoueur2(j2);
        h2.addCoupRes(j2);
        jeu.partieJouee(j1, j2);

        assertEquals("TRAHIR", jeu.getDernierCoupAdv(1));
        assertEquals("COOPERER", jeu.getDernierCoupAdv(2));

    }
    
}
