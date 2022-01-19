package com.example.strategie;

import java.util.ArrayList;

import com.example.joueur.*;

public class Rancunier implements Strategie{
	
	@Override
	public Coup prochainCoup(ArrayList<Coup> historiqueJ1, ArrayList<Coup> historiqueJ2) {
		Coup coup = Coup.COOPERER;
		for(Coup c : historiqueJ2) {
			if( c == Coup.TRAHIR){
				coup = Coup.TRAHIR;
			}
		}
		return coup;
			
	}

}
