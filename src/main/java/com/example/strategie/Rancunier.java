package com.example.strategie;

import java.util.ArrayList;

import com.example.joueur.*;

public class Rancunier extends Strategie{
	
	@Override
	public Coup prochainCoup(ArrayList<Coup> historiqueJ1, ArrayList<Coup> historiqueJ2) {
		Coup coup = null;
		try{
			for(Coup c : historiqueJ2) {
				if( c == Coup.TRAHIR){
					coup = Coup.TRAHIR;
				}
			}
		}
		catch(Exception e){
			coup = coupAleatoire(0.5);
		}
		return coup;
			
	}

}
