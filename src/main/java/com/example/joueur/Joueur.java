package com.example.joueur;

import com.example.partieDeJeux.Resultat;

public class Joueur {
	private String nom = null;
	private Coup coup = null;
	private Resultat resultat;
	private boolean connect;
	private Integer id;


	public Joueur(){}
	public Resultat getResultat() {
		return resultat;
	}

	public void setResultat(Resultat resultat) {
		this.resultat = resultat;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public Coup getCoup() {
		return coup;
	}

	public void setCoupString(String coupString) {
		switch(coupString){
			case "COOPERER" : 
				setCoup(coup);
				break;
			case "TRAHIR" :
				setCoup(coup);
				break;
		}
	}

	public void setCoup(Coup coup) {
		this.coup = coup;
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
