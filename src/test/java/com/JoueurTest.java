package com;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.joueur.*;


class JoueurTest {

	// Test Joueur.java 
	Joueur j = new Joueur();
	@Test
	void testJoueur(){
		assertNull(j.getNom());
 	}

	@Test
	void testConnectJoueur() {
		j.setConnect(true);
		assertTrue(j.isConnect());
	}

	@Test
	void testSetConnectJoueur() {
		j.setConnect(false);
		assertFalse(j.isConnect());
	}

	@Test
	void testSetGetCoupString() {
		j.setCoupString("abc");
		assertEquals(null, j.getCoup());

		j.setCoupString("COOPERER");
		assertEquals(Coup.COOPERER, j.getCoup());
		
		j.setCoupString("TRAHIR");
		
		assertEquals(Coup.TRAHIR, j.getCoup());
	}

	@Test
    void testSetGetId() {
        
	    Integer id = 1000;

		j.setId(id);

        assertEquals(1000, j.getId());
    }

	@Test
    void testSetGetNom() {
        
	    String nom ="test";

		j.setNom(nom);

        assertEquals("test", j.getNom());
    }

	@Test
    void testSetGetStrategie() {
        
	    int  i = 10;

		j.setStrategie(i);

        assertEquals(10, j.getStrategie());
    }


}
