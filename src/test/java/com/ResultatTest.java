package com;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.partiedejeux.*;


class ResultatTest {

    	//Test Resultat

	@Test
	void testGetValResultat(){
    	assertEquals(5, Resultat.T.getPoint());
	}
    
}
