package com.example.strategie;

import java.util.ArrayList;

import com.example.joueur.Coup;
import com.example.Tools;

public class Aleatoire implements Strategie{
	
	@Override
	public Coup prochainCoup(ArrayList<Coup> historiqueJ1, ArrayList<Coup> historiqueJ2) {
		return Tools.coupAleatoire(0.5);
	}
}
