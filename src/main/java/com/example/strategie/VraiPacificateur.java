package com.example.strategie;

import java.util.ArrayList;

import com.example.joueur.*;

import com.example.Tools;

public class VraiPacificateur implements Strategie{
	
	@Override
	public Coup prochainCoup(ArrayList<Coup> historiqueJ1, ArrayList<Coup> historiqueJ2) {
		Coup coup = Coup.COOPERER;
		try{
			if(Math.random()<0.7 && (historiqueJ2.get(historiqueJ2.size()-2) == Coup.TRAHIR ) && (historiqueJ2.get(historiqueJ2.size() - 1) == Coup.TRAHIR )){
				coup = Coup.TRAHIR;
			}
		}
		catch(Exception e){
			coup = Tools.coupAleatoire(0.5);
		}
		return coup;
		
	}


}
