package com.example.strategie;

import java.util.ArrayList;

import com.example.joueur.*;

public class Donnant2DonnantAleatoire extends Strategie{

	@Override
	public Coup ProchainCoup(ArrayList<Coup> historiqueJ1, ArrayList<Coup> historiqueJ2) {
		Coup coup = null;
		try {
			if(Math.random()<0.6 && (historiqueJ2.get(historiqueJ2.size() - 1) == historiqueJ2.get(historiqueJ2.size() - 2))) {
				coup = historiqueJ2.get(historiqueJ2.size() - 1);
			}
			else {
				 coup = CoupAleatoire(0.5);
			}
		}	
		catch(Exception e){
			coup =  CoupAleatoire(0.5);
		}
		return coup;
	}
	
}
