package com.example.strategie;

import java.util.ArrayList;

import com.example.joueur.*;
public class SondeurRepentant extends Strategie {
	
	@Override
	public Coup prochainCoup(ArrayList<Coup> historiqueJ1, ArrayList<Coup> historiqueJ2) {
		Coup coup = null;
		try {
			if((historiqueJ1.get(historiqueJ1.size()-1) == Coup.TRAHIR )&&(historiqueJ2.get(historiqueJ2.size()-1) == Coup.TRAHIR )){
				
				coup = Coup.COOPERER;
			}
			else if (historiqueJ2.get(historiqueJ2.size()-1) == Coup.COOPERER){
				if(Math.random()<0.5) {
					coup = Coup.TRAHIR;
				}
				else {
					coup = Coup.COOPERER;
				}
			}
		}
		catch(Exception e){
			coup = coupAleatoire(0.5);
		}
		return coup;
		
	}

}
