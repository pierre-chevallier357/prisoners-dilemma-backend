package com.example.strategie;

import java.util.ArrayList;

import com.example.joueur.*;

public class PacificateurNaif extends Strategie{
	
	@Override
	public Coup prochainCoup(ArrayList<Coup> historiqueJ1, ArrayList<Coup> historiqueJ2) {
		Coup coup;
		try {
			coup = historiqueJ2.get(historiqueJ2.size() - 1);
			if (coup == Coup.TRAHIR && Math.random()<0.5) {
				coup = Coup.COOPERER;
			}
		}		
		catch(Exception e){
			coup = coupAleatoire(0.5);
		}
		return coup;
		
	}
}
