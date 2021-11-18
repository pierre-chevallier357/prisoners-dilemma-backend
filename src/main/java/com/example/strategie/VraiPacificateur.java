package com.example.strategie;

import java.util.ArrayList;

import com.example.joueur.*;

public class VraiPacificateur extends Strategie{
	
	@Override
	public Coup ProchainCoup(ArrayList<Coup> historiqueJ1, ArrayList<Coup> historiqueJ2) {
		Coup coup = Coup.COOPERER;
		try{
			if(Math.random()<0.7 && (historiqueJ2.get(historiqueJ2.size()-2) == Coup.TRAHIR ) && (historiqueJ2.get(historiqueJ2.size() - 1) == Coup.TRAHIR )){
				coup = Coup.TRAHIR;
			}
		}
		catch(Exception e){
			coup = CoupAleatoire(0.5);
		}
		return coup;
		
	}


}
