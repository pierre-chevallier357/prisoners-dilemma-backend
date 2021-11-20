package com;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.example.joueur.*;
import com.example.partiedejeux.*;

class JeuTest {
    
    Jeu jeu = new Jeu();
    Joueur j1 = new Joueur();
    Joueur j2 = new Joueur();

    HistoriqueJoueur h1 = new HistoriqueJoueur();
    HistoriqueJoueur h2 = new HistoriqueJoueur();

    @Test
    void getInitJoueurTest() {
        assertEquals(j1.getClass().getSimpleName(), jeu.getJoueur1().getClass().getSimpleName());
        assertEquals(j2.getClass().getSimpleName(), jeu.getJoueur1().getClass().getSimpleName());
    }

    @Test
    void  setGetJoueurTest(){
        jeu.setJoueur1(j1);
        jeu.setJoueur2(j2);

        assertEquals(j1 , jeu.getJoueur1());
        assertEquals(j2 , jeu.getJoueur2());
    }

    @Test
    void ifPlayedTest(){
        assertEquals(false , jeu.ifPlayed());
        jeu.getJoueur1().setCoup(Coup.COOPERER);
        jeu.getJoueur2().setCoup(Coup.COOPERER);
        assertEquals(true , jeu.ifPlayed());
    }

    @Test
    void resetCoupJoueurTest(){
        jeu.getJoueur1().setCoup(Coup.COOPERER);
        jeu.getJoueur2().setCoup(Coup.COOPERER);
        jeu.resetCoupJoueur();
        assertNull(jeu.getJoueur1().getCoup());
        assertNull(jeu.getJoueur2().getCoup());
    }

    @Test
    void setGetNbTourTest(){
        jeu.setNbTour(10);
        assertEquals(10 , jeu.getNbTour());
    }

    
    @Test
    void setGetNbTourJoueeTest(){
        jeu.setNbTourJouee(10);
        assertEquals(10 , jeu.getNbTourJouee());
    }

    @Test
    void getPartieIdTest(){
        Integer i = 1;
        assertEquals(i.getClass().getSimpleName() , jeu.getPartieId().getClass().getSimpleName());
    }

    @Test
    void setStrategieTest(){
        jeu.getJoueur1().setId(1);
        jeu.getJoueur2().setId(2);
        jeu.setStrategie(jeu.getJoueur1().getId(), 1);
        assertEquals(1 , jeu.getJoueur1().getStrategie());
        jeu.setStrategie(jeu.getJoueur2().getId(), 1);
        assertEquals(1 , jeu.getJoueur2().getStrategie());
    }

    @Test
    void attenteDeCoupTest(){
        j1.setId(1);
        j1.setCoup(Coup.COOPERER);
        j1.setResultat(Resultat.P);
        j1.setConnect(true);
        j2.setId(2);
        jeu.setJoueur1(j1);
        
        j2.setCoup(Coup.TRAHIR);
        j2.setResultat(Resultat.C);
        
        j2.setConnect(true);
        jeu.setJoueur2(j2);

        jeu.partieJouee(j1, j2);

        assertTrue(jeu.attenteDeCoup());

    }

    @Test
    void attenteJoueur2Test(){
        j1.setId(1);
        j1.setCoup(Coup.COOPERER);
        j1.setResultat(Resultat.P);
        j1.setConnect(true);
        j2.setId(2);
        jeu.setJoueur1(j1);
        
        j2.setCoup(Coup.TRAHIR);
        j2.setResultat(Resultat.C);
        
        j2.setConnect(true);
        jeu.setJoueur2(j2);
        jeu.partieJouee(j1, j2);
        assertTrue(jeu.attenteJoueur2(1));
        assertTrue(jeu.attenteJoueur2(2));

    }

    @Test
    void getResAdvTest(){
        j1.setId(1);
        j1.setCoup(Coup.COOPERER);
        j1.setResultat(Resultat.P);
        j2.setId(2);
        jeu.setJoueur1(j1);
        
        j2.setCoup(Coup.TRAHIR);
        j2.setResultat(Resultat.C);

        jeu.setJoueur2(j2);
        
        h1.addCoupRes(j1);
        h2.addCoupRes(j2);

        jeu.partieJouee(j1, j2);

        assertEquals("5", jeu.getResAdv(1));
        assertEquals("0", jeu.getResAdv(2));

    }

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

    @Test
    void joueUnCoupTest(){
        j1.setId(1);
        jeu.setJoueur1(j1);
        jeu.joueUnCoup(jeu.getPartieId(), j1.getId(), "COOPERER");
        assertEquals(Coup.COOPERER, jeu.getJoueur1().getCoup());

        j2.setId(2);
        jeu.setJoueur2(j2);
        jeu.joueUnCoup(jeu.getPartieId(), j2.getId(), "TRAHIR");
        assertEquals(Coup.TRAHIR, jeu.getJoueur2().getCoup());

    }



    @Test
    void partieJoueeTest(){
        j1.setCoup(Coup.COOPERER);
        jeu.setJoueur1(j1);
        j2.setCoup(Coup.COOPERER);
        jeu.setJoueur2(j2);
        jeu.partieJouee(j1, j2);
        assertEquals(Resultat.C, jeu.getJoueur1().getResultat());
        assertEquals(Resultat.C, jeu.getJoueur2().getResultat());

        j1.setCoup(Coup.TRAHIR);
        jeu.setJoueur1(j1);
        j2.setCoup(Coup.TRAHIR);
        jeu.setJoueur2(j2);  
        jeu.partieJouee(j1, j2);
        assertEquals(Resultat.P, jeu.getJoueur1().getResultat());
        assertEquals(Resultat.P, jeu.getJoueur2().getResultat());

        j1.setCoup(Coup.TRAHIR);
        jeu.setJoueur1(j1);
        j2.setCoup(Coup.COOPERER);
        jeu.setJoueur2(j2);  
        jeu.partieJouee(j1, j2);
        assertEquals(Resultat.T, jeu.getJoueur1().getResultat());
        assertEquals(Resultat.D, jeu.getJoueur2().getResultat());

        j1.setCoup(Coup.COOPERER);
        jeu.setJoueur1(j1);
        j2.setCoup(Coup.TRAHIR);
        jeu.setJoueur2(j2);  
        jeu.partieJouee(j1, j2);
        assertEquals(Resultat.D, jeu.getJoueur1().getResultat());
        assertEquals(Resultat.T, jeu.getJoueur2().getResultat());

    }
    

}
