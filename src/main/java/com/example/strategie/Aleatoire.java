package com.example.strategie;

import java.util.ArrayList;

import com.example.joueur.Coup;
import com.example.joueur.Joueur;

public class Aleatoire extends Strategie{
	
	@Override
	public Coup ProchainCoup(Joueur joueur, ArrayList<Coup> historiqueJ1, ArrayList<Coup> historiqueJ2) {
		return CoupAleatoire(0.5);
		
	}
}
