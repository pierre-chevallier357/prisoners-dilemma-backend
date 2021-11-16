package com.example.joueur;

import com.example.partieDeJeux.Resultat;

public class Joueur {
	private String nom = new String();
	private Coup coup = null;
	private Resultat resultat;
	private boolean connect;

	public Resultat getResultat() {
		return resultat;
	}

	public void setResultat(Resultat resultat) {
		this.resultat = resultat;
	}

	public Coup getCoup() {
		return coup;
	}

	public void setCoup(Coup Coup) {
		this.coup = Coup;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public boolean isConnect() {
		return connect;
	}

	public void setConnect(boolean connect) {
		this.connect = connect;
	}
	
	
}
