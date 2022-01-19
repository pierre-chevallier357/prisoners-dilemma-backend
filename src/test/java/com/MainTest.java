package com;    

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


import com.example.Main;
import com.example.joueur.Coup;
import com.example.partiedejeux.Jeu;

class MainTest {
    Main main = new Main();
    Integer idJoueur = main.creationJoueurUser("Joueur1");
    Integer idPartie = main.creationPartieUser(idJoueur, 1);


    @Test
    void indexUserTest() {
        String UserTest = "Ceci est le serveur, pour jouer, veuillez aller sur l'application : client-pc.web.app";
        assertEquals(UserTest, main.indexUser());
    }

    @Test
    void creationJoueurUserTest() {
        assertEquals(main.getAllIdsJoueurUser(), Integer.toString(idJoueur)+"&");
    }

    @Test
    void creationPartieUserTest() {
        assertEquals(main.getAllPartieUser(), Integer.toString(idPartie)+"&");
    }
    


    @Test
    void rejoindrePartieUserTest() {
        Integer idJoueur2 = main.creationJoueurUser("Joueur2");
        assertTrue(main.rejoindrePartieUser(idPartie, idJoueur2));
    }

    @Test
    void attenteDuJoueur2UserTest() {
        Integer idJoueur2 = main.creationJoueurUser("Joueur2");
        main.rejoindrePartieUser(idPartie, idJoueur2);
        assertTrue(main.attenteDuJoueur2User(idPartie, idJoueur));
    }


    @Test
    void nbCoupsJouesUserTest() {
        assertEquals("1", main.nbCoupsJouesUser(idPartie));
    }

    @Test
    void coupJoueurUserTest() {
        assertTrue(main.coupJoueurUser(idPartie, idJoueur, "COOPERER"));
        Integer i = 1;
        assertFalse(main.coupJoueurUser(idPartie, i, "COOPERER"));
    }

    @Test
    void strategieJoueurUserTest() {
        assertTrue(main.strategieJoueurUser(idPartie, idJoueur, 1));
    }
    
    @Test
    void disconnectJoueurUserTest() {
        assertTrue(main.disconnectJoueurUser(idPartie, idJoueur));
    }

    @Test
    void jouePartieUserTest() {
        Integer idJoueur2 = main.creationJoueurUser("Joueur2");
        main.coupJoueurUser(idPartie, idJoueur, "COOPERER");
        main.coupJoueurUser(idPartie, idJoueur2, "COOPERER");
        assertTrue(main.jouePartieUser(idPartie, idJoueur));
        main.strategieJoueurUser(idPartie, idJoueur, 1);
        main.coupJoueurUser(idPartie, idJoueur2, "COOPERER");
        assertTrue(main.jouePartieUser(idPartie, idJoueur2));
    }

    @Test
    void resultatTourUserTest() {
        Integer idJoueur2 = main.creationJoueurUser("Joueur2");
        
        main.coupJoueurUser(idPartie, idJoueur, "COOPERER");
        main.coupJoueurUser(idPartie, idJoueur2, "TRAHIR");
        main.jouePartieUser(idPartie, idJoueur);
        assertEquals("0", main.resultatTourUser(idPartie, idJoueur));
    }

    @Test
    void dernierCoupAdvUserTest() {
        Integer idJoueur2 = main.creationJoueurUser("Joueur2");
        main.rejoindrePartieUser(idPartie, idJoueur2);
        Jeu jeu = main.getJeuUser();
        jeu.getJoueur1().setCoup(Coup.COOPERER);
        jeu.getJoueur2().setCoup(Coup.COOPERER);
        main.jouePartieUser(idPartie, idJoueur);
        assertEquals("COOPERER", main.dernierCoupAdvUser(idPartie, idJoueur));
    }

    @Test
    void resFinalUserTest() {
        Integer idJoueur2 = main.creationJoueurUser("Joueur2");
        main.rejoindrePartieUser(idPartie, idJoueur2);
        Jeu jeu = main.getJeuUser();
        jeu.getJoueur1().setCoup(Coup.COOPERER);
        jeu.getJoueur2().setCoup(Coup.COOPERER);
        main.jouePartieUser(idPartie, idJoueur);
        assertEquals("3&3", main.resFinalUser(idPartie, idJoueur));
    }

    @Test
    void  getAllJoueurUserTest() {
        Integer idJoueur2 = main.creationJoueurUser("Joueur2");
        assertEquals("Joueur1&"+idJoueur+"&"+"Joueur2&"+idJoueur2+"&", main.getAllJoueurUser());
    }

    @Test
    void  getAllIdsJoueurUserTest() {
        Integer idJoueur2 = main.creationJoueurUser("Joueur2");
        assertEquals(idJoueur+"&"+idJoueur2+"&", main.getAllIdsJoueurUser());
    }

    @Test
    void  getAllNomsJoueurUserTest() {
        main.creationJoueurUser("Joueur2");
        assertEquals("Joueur1&"+"Joueur2&", main.getAllNomsJoueurUser());
    }
    
    @Test
    void  getAllPartieUserTest() {
        assertEquals(idPartie+"&", main.getAllPartieUser());
    }
    @Test
    void  getJeuUserTest() {
        Jeu jeu = new Jeu();
        assertEquals(jeu.getClass().getName(), main.getJeuUser().getClass().getName());
    }

    @Test
    void  supprimerPartieUserTest() {
        assertEquals(true, main.supprimerPartieUser(idPartie));
    }
}
