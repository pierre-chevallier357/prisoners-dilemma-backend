package Test;

import org.junit.jupiter.api.Test;
import java.com.example.*;

import com.example.joueur.Coup;
import com.example.joueur.Joueur;
import com.example.partiedejeux.HistoriqueJoueur;
import com.example.partiedejeux.Resultat;

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
		;
		assertEquals(Coup.COOPERER, j.getCoup());
	}

	@Test
	public void testGetCoupString() {
		Joueur j = new Joueur();
		j.setCoupString("COOPERER");
		;
		assertEquals(Coup.COOPERER, j.isConnect());
	}

	//Test HistoriqueJoueur.java

	@Test
	public void testLastCoup(){
		Joueur j = new Joueur();
		j.setCoup(Coup.COOPERER);
		j.setResultat(Resultat.P);
		HistoriqueJoueur hj = new HistoriqueJoueur();
		hj.addCoupRes(j);
    	assertEquals(Coup.COOPERER, h1.getDerniersCoup());
	}

	@Test
	public void testCalculRes(){
		Joueur j = new Joueur();
		j.setCoup(Coup.COOPERER);
		j.setResultat(Resultat.P);
		HistoriqueJoueur hj = new HistoriqueJoueur();
		hj.addCoupRes(j);
    	assertEquals(1, h1.calculPoint());
	}

}
