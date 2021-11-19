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
		j.setCoupString("COOPERER");
		assertEquals(Coup.COOPERER, j.getCoup());
	}

}
