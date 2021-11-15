package test;
import partieDeJeux.*;
import joueur.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestJeu {

	@Test
	void testResultat() {
		Resultat res = Resultat.C;
		assertEquals(3, res.getPoint());
	}


}
