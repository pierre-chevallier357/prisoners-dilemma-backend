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

	//Test HistoriqueJoueur.java

	@Test
	public void testLastCoup(){
		Joueur j = new Joueur();
		j.setCoup(Coup.COOPERER);
		j.setResultat(Resultat.P);
		HistoriqueJoueur hj = new HistoriqueJoueur();
		hj.addCoupRes(j);
    	assertEquals(Coup.COOPERER, hj.getLastCoup());
	}

	@Test
	public void testCalculRes(){
		Joueur j = new Joueur();
		j.setCoup(Coup.COOPERER);
		j.setResultat(Resultat.P);
		HistoriqueJoueur hj = new HistoriqueJoueur();
		hj.addCoupRes(j);
    	assertEquals(1, hj.calculPoint());
	}

}
