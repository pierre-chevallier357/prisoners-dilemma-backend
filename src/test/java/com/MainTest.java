package com;    

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.example.Main;
import com.example.joueur.Coup;
import com.example.partiedejeux.Jeu;

class MainTest {
    Main main = new Main();
    Integer idJoueur = main.creationJoueur("Joueur1");
    Integer idPartie = main.creationPartie(idJoueur, 1);


    @Test
    void indexTest() {
        String test = "Ceci est le serveur voici l'application : client-pc.web.app";
        assertEquals(test, main.index());
    }

    @Test
    void creationJoueurTest() {
        assertEquals(main.getAllIdsJoueur(), Integer.toString(idJoueur)+"&");
    }

    @Test
    void creationPartieTest() {
        assertEquals(main.getAllPartie(), Integer.toString(idPartie)+"&");
    }
    


    @Test
    void rejoindrePartieTest() {
        Integer idJoueur2 = main.creationJoueur("Joueur2");
        assertTrue(main.rejoindrePartie(idPartie, idJoueur2));
    }

    @Test
    void attenteDuJoueur2Test() {
        Integer idJoueur2 = main.creationJoueur("Joueur2");
        main.rejoindrePartie(idPartie, idJoueur2);
        assertTrue(main.attenteDuJoueur2(idPartie, idJoueur));
    }


    @Test
    void nbCoupsJouesTest() {
        assertEquals("1", main.nbCoupsJoues(idPartie));
    }

    @Test
    void coupJoueurTest() {
        assertTrue(main.coupJoueur(idPartie, idJoueur, "COOPERER"));
        Integer i = 1;
        assertFalse(main.coupJoueur(idPartie, i, "COOPERER"));
    }

    @Test
    void strategieJoueurTest() {
        assertTrue(main.strategieJoueur(idPartie, idJoueur, 1));
    }

    @Test
    void jouePartieTest() {
        Integer idJoueur2 = main.creationJoueur("Joueur2");
        main.coupJoueur(idPartie, idJoueur, "COOPERER");
        main.coupJoueur(idPartie, idJoueur2, "COOPERER");
        assertTrue(main.jouePartie(idPartie, idJoueur));
        main.strategieJoueur(idPartie, idJoueur, 1);
        main.coupJoueur(idPartie, idJoueur2, "COOPERER");
        assertTrue(main.jouePartie(idPartie, idJoueur2));
    }

    @Test
    void resultatTourTest() {
        Integer idJoueur2 = main.creationJoueur("Joueur2");
        
        main.coupJoueur(idPartie, idJoueur, "TRAHIR");
        main.coupJoueur(idPartie, idJoueur2, "COOPERER");
        main.jouePartie(idPartie, idJoueur);
        assertEquals("0", main.resultatTour(idPartie, idJoueur));
    }

    @Test
    void dernierCoupAdvTest() {
        Integer idJoueur2 = main.creationJoueur("Joueur2");
        main.rejoindrePartie(idPartie, idJoueur2);
        Jeu jeu = main.getJeu();
        jeu.getJoueur1().setCoup(Coup.COOPERER);
        jeu.getJoueur2().setCoup(Coup.COOPERER);
        main.jouePartie(idPartie, idJoueur);
        assertEquals("COOPERER", main.dernierCoupAdv(idPartie, idJoueur));
    }

    @Test
    void resFinalTest() {
        Integer idJoueur2 = main.creationJoueur("Joueur2");
        main.rejoindrePartie(idPartie, idJoueur2);
        Jeu jeu = main.getJeu();
        jeu.getJoueur1().setCoup(Coup.COOPERER);
        jeu.getJoueur2().setCoup(Coup.COOPERER);
        main.jouePartie(idPartie, idJoueur);
        assertEquals("3&3", main.resFinal(idPartie, idJoueur));
    }

    @Test
    void  getAllJoueurTest() {
        Integer idJoueur2 = main.creationJoueur("Joueur2");
        assertEquals("Joueur1&"+idJoueur+"&"+"Joueur2&"+idJoueur2+"&", main.getAllJoueur());
    }

    @Test
    void  getAllIdsJoueurTest() {
        Integer idJoueur2 = main.creationJoueur("Joueur2");
        assertEquals(idJoueur+"&"+idJoueur2+"&", main.getAllIdsJoueur());
    }

    @Test
    void  getAllNomsJoueurTest() {
        main.creationJoueur("Joueur2");
        assertEquals("Joueur1&"+"Joueur2&", main.getAllNomsJoueur());
    }
    
    @Test
    void  getAllPartieTest() {
        assertEquals(idPartie+"&", main.getAllPartie());
    }
    @Test
    void  getJeuTest() {
        Jeu jeu = new Jeu();
        assertEquals(jeu.getClass().getName(), main.getJeu().getClass().getName());
    }

    @Test
    void  supprimerPartieTest() {
        assertEquals(true, main.supprimerPartie(idPartie));
    }
}
