package com;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.partiedejeux.*;


public class ResultatTest {

    	//Test Resultat

	@Test
	public void testGetValResultat(){
    	assertEquals(5, Resultat.T.getPoint());
	}
    
}
