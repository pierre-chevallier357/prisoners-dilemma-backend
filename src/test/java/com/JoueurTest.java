package com;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.joueur.*;


public class JoueurTest {

	// Test Joueur.java 

	@Test
	void testConnectJoueur() {
		Joueur j = new Joueur();
		j.setConnect(true);
		assertEquals(true, j.isConnect());
	}

	@Test
	public void testSetConnectJoueur() {
		Joueur j = new Joueur();
		j.setConnect(false);
		assertEquals(false, j.isConnect());
	}

	@Test
	public void testSetGetCoupString() {
		Joueur j = new Joueur();
		j.setCoupString("COOPERER");
		assertEquals(Coup.COOPERER, j.getCoup());
	}

}
