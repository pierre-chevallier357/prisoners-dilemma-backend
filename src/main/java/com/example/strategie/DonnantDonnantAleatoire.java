package com.example.strategie;

import java.util.ArrayList;

import com.example.joueur.*;

import com.example.Tools;
public class DonnantDonnantAleatoire implements Strategie{
	@Override
	public Coup prochainCoup(ArrayList<Coup> historiqueJ1, ArrayList<Coup> historiqueJ2) {
		Coup coup;
		try {
			if(Math.random()<0.6) {
				coup = historiqueJ2.get(historiqueJ2.size() - 1);
			}
			else {
				coup = Tools.coupAleatoire(0.5);
			}
		}
		catch(Exception e){
			coup = Tools.coupAleatoire(0.5);
		}
		return coup;
	}
}
