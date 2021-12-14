package com.example.Strategie_groupe2;

import java.util.ArrayList;

public class StrategieToujourTrahir implements Strategie {

	@Override
	public Choix jouerStrategie(ArrayList<Choix> choixAdversaire, int numeroTour) {
		return Choix.TRAHIR;	
	}
}
