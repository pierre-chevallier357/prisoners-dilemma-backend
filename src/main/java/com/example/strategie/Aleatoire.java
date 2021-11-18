package com.example.strategie;

import java.util.ArrayList;

import com.example.joueur.Coup;

public class Aleatoire extends Strategie{
	
	@Override
	public Coup ProchainCoup(ArrayList<Coup> historiqueJ1, ArrayList<Coup> historiqueJ2) {
		return CoupAleatoire(0.5);
		
	}
}
