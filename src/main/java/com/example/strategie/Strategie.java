package com.example.strategie;

import java.util.ArrayList;

import com.example.joueur.*;

public abstract class Strategie {
	public abstract Coup prochainCoup(ArrayList<Coup> historiqueJ1, ArrayList<Coup> historiqueJ2);

	
	public Coup coupAleatoire(double d) {
		Coup coup;
		
		if(Math.random()<d) {
			coup= Coup.COOPERER;
		}
		else {
			coup = Coup.TRAHIR;
		}
		
		return coup;
		
		
	}
}
