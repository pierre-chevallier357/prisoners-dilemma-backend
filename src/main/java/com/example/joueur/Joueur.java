package com.example.joueur;

import com.example.partieDeJeux.Resultat;

public class Joueur {
	private String nom = null;
	private Coup coup = null;
	private Resultat resultat;
	private boolean connect;
	private Integer id;
	private int strategie = 0;


	public Joueur(){
		this.connect = false;
	}
	public Resultat getResultat() {
		return resultat;
	}

	public void setResultat(Resultat resultat) {
		this.resultat = resultat;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public Coup getCoup() {
		return coup;
	}

	public void setCoupString(String coupString) {
		switch(coupString){
			case "COOPERER" : 
				setCoup(Coup.COOPERER);
				break;
			case "TRAHIR" :
				setCoup(Coup.TRAHIR);
				break;
			default:
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

	public void setStrategie(int strategie) {
		this.strategie = strategie;
	}

	public int getStrategie() {
		return strategie;
	}
	
	
}
