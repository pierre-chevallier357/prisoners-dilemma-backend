package com.example.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.*;
import com.example.joueur.*;
import com.example.partiedejeux.*;
import com.example.strategie.*;

public class test {

	// Test Joueur.java 

	@Test
	public void testConnectJoueur() {
		Joueur j = new Joueur();
		j.setConnect(true);
		assertEquals(true, j.isConnect());
	}
}
