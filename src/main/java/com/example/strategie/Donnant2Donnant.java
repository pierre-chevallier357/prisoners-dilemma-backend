package com.example.strategie;

import java.util.ArrayList;

import com.example.joueur.*;

public class Donnant2Donnant extends Strategie {
	@Override
	public Coup ProchainCoup(ArrayList<Coup> historiqueJ1, ArrayList<Coup> historiqueJ2) {
		Coup coup = null;
		try {
			int i = historiqueJ2.size() - 1;
			 while(historiqueJ2.get(i) != historiqueJ2.get(i-1)) {
				 i--;
			 }
			 if(i>0) {
				 coup = historiqueJ2.get(historiqueJ2.size() - 1);
			 }	
		}		
		catch(Exception e){
			coup = CoupAleatoire(0.5);
		}
		 return coup;
	}
}
