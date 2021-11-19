package com;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.joueur.*;


public class JoueurTest {

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
	public void testSetConnectJoueur() {
		j.setConnect(false);
		assertFalse(j.isConnect());
	}

	@Test
	public void testSetGetCoupString() {
		j.setCoupString("COOPERER");
		assertEquals(Coup.COOPERER, j.getCoup());
	}

}
