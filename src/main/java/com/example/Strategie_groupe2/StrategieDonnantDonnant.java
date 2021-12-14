package com.example.Strategie_groupe2;

import java.util.ArrayList;

public class StrategieDonnantDonnant implements Strategie{

	private String nom = "DonnantDonnant";
	
	public StrategieDonnantDonnant() {
		
	}
	
	public String getNom() {
		return this.nom;
	}

	@Override
	public Choix jouerStrategie(ArrayList<Choix> choixAdversaire, int Tnbr) {
		return choixAdversaire.get(Tnbr-1);
	}
	
	
	

}
