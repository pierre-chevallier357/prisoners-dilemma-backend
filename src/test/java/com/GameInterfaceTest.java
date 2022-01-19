package com;    

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.example.GameInterface;
import com.example.GameRequest;
import com.example.joueur.Coup;
import com.example.partiedejeux.Jeu;

class GameInterfaceTest {
    GameInterface game = new GameRequest();
    Integer idJoueur = game.creationJoueur("Joueur1");
    Integer idPartie = game.creationPartie(idJoueur, 1);


    @Test
    void indexTest() {
        String test = "Ceci est le serveur, pour jouer, veuillez aller sur l'application : client-pc.web.app";
        assertEquals(test, game.index());
    }

    @Test
    void creationJoueurTest() {
        assertEquals(game.getAllIdsJoueur(), Integer.toString(idJoueur)+"&");
    }

    @Test
    void creationPartieTest() {
        assertEquals(game.getAllPartie(), Integer.toString(idPartie)+"&");
    }
    


    @Test
    void rejoindrePartieTest() {
        Integer idJoueur2 = game.creationJoueur("Joueur2");
        assertTrue(game.rejoindrePartie(idPartie, idJoueur2));
    }

    @Test
    void attenteDuJoueur2Test() {
        Integer idJoueur2 = game.creationJoueur("Joueur2");
        game.rejoindrePartie(idPartie, idJoueur2);
        assertTrue(game.attenteDuJoueur2(idPartie, idJoueur));
    }


    @Test
    void nbCoupsJouesTest() {
        assertEquals("1", game.nbCoupsJoues(idPartie));
    }

    @Test
    void coupJoueurTest() {
        assertTrue(game.coupJoueur(idPartie, idJoueur, "COOPERER"));
        Integer i = 1;
        assertFalse(game.coupJoueur(idPartie, i, "COOPERER"));
    }

    @Test
    void strategieJoueurTest() {
        assertTrue(game.strategieJoueur(idPartie, idJoueur, 1));
    }

    @Test
    void jouePartieTest() {
        Integer idJoueur2 = game.creationJoueur("Joueur2");
        game.coupJoueur(idPartie, idJoueur, "COOPERER");
        game.coupJoueur(idPartie, idJoueur2, "COOPERER");
        assertTrue(game.jouePartie(idPartie, idJoueur));
        game.strategieJoueur(idPartie, idJoueur, 1);
        game.coupJoueur(idPartie, idJoueur2, "COOPERER");
        assertTrue(game.jouePartie(idPartie, idJoueur2));
    }

    @Test
    void resultatTourTest() {
        Integer idJoueur2 = game.creationJoueur("Joueur2");
        
        game.coupJoueur(idPartie, idJoueur, "COOPERER");
        game.coupJoueur(idPartie, idJoueur2, "TRAHIR");
        game.jouePartie(idPartie, idJoueur);
        assertEquals("0", game.resultatTour(idPartie, idJoueur));
    }

    @Test
    void dernierCoupAdvTest() {
        Integer idJoueur2 = game.creationJoueur("Joueur2");
        game.rejoindrePartie(idPartie, idJoueur2);
        Jeu jeu = game.getJeu();
        jeu.getJoueur1().setCoup(Coup.COOPERER);
        jeu.getJoueur2().setCoup(Coup.COOPERER);
        game.jouePartie(idPartie, idJoueur);
        assertEquals("COOPERER", game.dernierCoupAdv(idPartie, idJoueur));
    }

    @Test
    void resFinalTest() {
        Integer idJoueur2 = game.creationJoueur("Joueur2");
        game.rejoindrePartie(idPartie, idJoueur2);
        Jeu jeu = game.getJeu();
        jeu.getJoueur1().setCoup(Coup.COOPERER);
        jeu.getJoueur2().setCoup(Coup.COOPERER);
        game.jouePartie(idPartie, idJoueur);
        assertEquals("3&3", game.resFinal(idPartie, idJoueur));
    }

    @Test
    void  getAllJoueurTest() {
        Integer idJoueur2 = game.creationJoueur("Joueur2");
        assertEquals("Joueur1&"+idJoueur+"&"+"Joueur2&"+idJoueur2+"&", game.getAllJoueur());
    }

    @Test
    void  getAllIdsJoueurTest() {
        Integer idJoueur2 = game.creationJoueur("Joueur2");
        assertEquals(idJoueur+"&"+idJoueur2+"&", game.getAllIdsJoueur());
    }

    @Test
    void  getAllNomsJoueurTest() {
        game.creationJoueur("Joueur2");
        assertEquals("Joueur1&"+"Joueur2&", game.getAllNomsJoueur());
    }
    
    @Test
    void  getAllPartieTest() {
        assertEquals(idPartie+"&", game.getAllPartie());
    }
    @Test
    void  getJeuTest() {
        Jeu jeu = new Jeu();
        assertEquals(jeu.getClass().getName(), game.getJeu().getClass().getName());
    }

    @Test
    void  supprimerPartieTest() {
        assertEquals(true, game.supprimerPartie(idPartie));
    }
}
