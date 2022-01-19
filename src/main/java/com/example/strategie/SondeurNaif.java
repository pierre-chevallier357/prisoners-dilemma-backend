package com.example.strategie;

import java.util.ArrayList;

import com.example.joueur.*;

import com.example.Tools;

public class SondeurNaif implements Strategie{
	@Override
	public Coup prochainCoup(ArrayList<Coup> historiqueJ1, ArrayList<Coup> historiqueJ2) {
		Coup coup;
		try {
				coup = historiqueJ2.get(historiqueJ2.size() - 1);
				if (coup == Coup.COOPERER && Math.random()<0.5) {
					coup = Coup.TRAHIR;
				}
		}
		catch(Exception e){
			coup = Tools.coupAleatoire(0.5);
		}
		return coup ;
		
	}

}
