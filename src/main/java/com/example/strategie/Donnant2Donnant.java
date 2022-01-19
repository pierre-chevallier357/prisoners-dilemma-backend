package com.example.strategie;

import java.util.ArrayList;

import com.example.joueur.*;

import com.example.Tools;

public class Donnant2Donnant implements Strategie {
	@Override
	public Coup prochainCoup(ArrayList<Coup> historiqueJ1, ArrayList<Coup> historiqueJ2) {
		Coup coup = null;
		try {
			int i = historiqueJ2.size() - 1;
			 while(historiqueJ2.get(i) != historiqueJ2.get(i-1)) {
				 i--;
			 }
			 if(i>0) {
				 coup = historiqueJ2.get(i);
			 }	
		}		
		catch(Exception e){
			coup = Tools.coupAleatoire(0.5);
		}
		 return coup;
	}
}
