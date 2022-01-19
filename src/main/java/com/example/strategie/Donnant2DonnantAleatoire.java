package com.example.strategie;

import java.util.ArrayList;

import com.example.joueur.*;

import com.example.Tools;
public class Donnant2DonnantAleatoire implements Strategie{

	@Override
	public Coup prochainCoup(ArrayList<Coup> historiqueJ1, ArrayList<Coup> historiqueJ2) {
		Coup coup = null;
		try {
			if(Math.random()<0.6) {
				int i = historiqueJ2.size() - 1;
				while(historiqueJ2.get(i) != historiqueJ2.get(i-1)) {
					i--;
				}
				if(i>0) {
					coup = historiqueJ2.get(i);
				}	
			}
			else {
				 coup = Tools.coupAleatoire(0.5);
			}
		}	
		catch(Exception e){
			coup =  Tools.coupAleatoire(0.5);
		}
		return coup;
	}
	
}
