package com.example.partieDeJeux;

public enum Resultat {

	T (5),
	D (0),
	C (3),
	P (1);
	
	private int val;
	
	Resultat(int nb) {
		val = nb;
		
	}

	public int getPoint() {
		return val;
	}
}
