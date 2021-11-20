package com;

import java.util.ArrayList;
import java.util.Random;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import com.example.Tools;
import com.example.joueur.Coup;
import com.example.joueur.Joueur;
import com.example.partiedejeux.HistoriqueJoueur;
import com.example.partiedejeux.Jeu;


public class ToolsTest {
    ArrayList<Jeu> listPartie = new ArrayList<>();
    ArrayList<Joueur> listJoueur = new ArrayList<>();
	HistoriqueJoueur historiqueJ1 = new HistoriqueJoueur();
	HistoriqueJoueur historiqueJ2 = new HistoriqueJoueur(); 
    Jeu jeu = new Jeu();
    Joueur joueur = new Joueur();
    Integer idJeu = jeu.getPartieId();
    Integer idJoueur = 10;
    
    @Test 
    void randomNumTest(){
        Integer i =1;
        assertEquals(i.getClass().getSimpleName(), Tools.randomNum().getClass().getSimpleName());
    }

    @Test
    void jeuDansListTest(){
        assertNull(Tools.jeuDansList(listPartie, idJeu));
        listPartie.add(jeu);
        assertEquals(jeu, Tools.jeuDansList(listPartie, idJeu));
    }

    @Test
    void joueurDansListTest(){
        joueur.setId(idJoueur);
        assertNull(Tools.joueurDansList(listJoueur, idJoueur));
        listJoueur.add(joueur);
        assertEquals(joueur, Tools.joueurDansList(listJoueur, idJoueur));
    }

    @Test
    void prochainCoupStratTest(){
        Coup coup = Coup.COOPERER;
        assertNull(Tools.prochainCoupStrat(historiqueJ1, historiqueJ2, 0));
        assertEquals(coup.getClass().getSimpleName() , Tools.prochainCoupStrat(historiqueJ1, historiqueJ2, 1).getClass().getSimpleName());
        assertEquals(coup.getClass().getSimpleName() , Tools.prochainCoupStrat(historiqueJ1, historiqueJ2, 2).getClass().getSimpleName());
        assertEquals(coup.getClass().getSimpleName() , Tools.prochainCoupStrat(historiqueJ1, historiqueJ2, 3).getClass().getSimpleName());
        assertEquals(coup.getClass().getSimpleName() , Tools.prochainCoupStrat(historiqueJ1, historiqueJ2, 4).getClass().getSimpleName());
        assertEquals(coup.getClass().getSimpleName() , Tools.prochainCoupStrat(historiqueJ1, historiqueJ2, 5).getClass().getSimpleName());
        assertEquals(coup.getClass().getSimpleName() , Tools.prochainCoupStrat(historiqueJ1, historiqueJ2, 6).getClass().getSimpleName());
        assertEquals(coup.getClass().getSimpleName() , Tools.prochainCoupStrat(historiqueJ1, historiqueJ2, 7).getClass().getSimpleName());
        assertEquals(coup.getClass().getSimpleName() , Tools.prochainCoupStrat(historiqueJ1, historiqueJ2, 8).getClass().getSimpleName());
        assertEquals(coup.getClass().getSimpleName() , Tools.prochainCoupStrat(historiqueJ1, historiqueJ2, 9).getClass().getSimpleName());
        assertEquals(coup.getClass().getSimpleName() , Tools.prochainCoupStrat(historiqueJ1, historiqueJ2, 10).getClass().getSimpleName());
        assertEquals(coup.getClass().getSimpleName() , Tools.prochainCoupStrat(historiqueJ1, historiqueJ2, 11).getClass().getSimpleName());
        assertEquals(coup.getClass().getSimpleName() , Tools.prochainCoupStrat(historiqueJ1, historiqueJ2, 12).getClass().getSimpleName());

    }
}
