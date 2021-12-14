package com.example.Strategie_groupe2;

import java.util.ArrayList;

public interface Strategie {		
	public Choix jouerStrategie (ArrayList<Choix> choixAdversaire, int numeroTour);

}
