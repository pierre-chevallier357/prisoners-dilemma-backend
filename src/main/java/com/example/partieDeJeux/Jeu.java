package com.example.partiedejeux;

import com.example.Tools;
import com.example.joueur.*;

public class Jeu{
	
	private Joueur joueur1 = new Joueur();
	private Joueur joueur2 = new Joueur();
	private Integer id;
	private int nbTourJouee = 1;
	private int nbTour;
	
	private HistoriqueJoueur historiqueJ1 = new HistoriqueJoueur();
	private HistoriqueJoueur historiqueJ2 = new HistoriqueJoueur();



	public Jeu(){
		this.id = Tools.randomNum();
	}

	public synchronized void attenteDeCoup(){
		boolean iHaveWait = false;
		if(joueur1.isConnect() && joueur2.isConnect()){
			while (!ifPlayed()) {
				try {
					iHaveWait = true;
					wait();
				} catch (Exception e) {
					Thread.currentThread().interrupt();
				}
			}
			if(!iHaveWait){
				notifyAll();
			}
		}
	}

	public synchronized void attenteJoueur2(Integer idJoueur){
		if(joueur1.getId().equals(idJoueur)){
			while (!joueur2.isConnect()) {
				try {
					wait();
				} catch (Exception e) {
					Thread.currentThread().interrupt();
				}
			}
		}
		else{
			notifyAll();
		}
	}

	public void jeuManche(){
		if(!joueur1.isConnect()){
			joueur1.setCoup(Tools.prochainCoupStrat(historiqueJ1, historiqueJ2, joueur1.getStrategie()));	
		}
		if(!joueur2.isConnect()){
			joueur2.setCoup(Tools.prochainCoupStrat(historiqueJ2, historiqueJ1, joueur2.getStrategie()));	
		}
		
		partieJouee(this.joueur1, this.joueur2);

	}

	public String getRes(Integer idJoueur){
		String res ="";
		if(joueur1.getId().equals(idJoueur)){
			res = Integer.toString(historiqueJ1.calculPoint());
		}
		else if(joueur2.getId().equals(idJoueur)){
			res = Integer.toString(historiqueJ2.calculPoint());
		}
		return res;
	}

	public String getResAdv(Integer idJoueur){
		String res ="";
		if(joueur2.getId().equals(idJoueur)){
			res = Integer.toString(historiqueJ1.calculPoint());
		}
		else if(joueur1.getId().equals(idJoueur)){
			res = Integer.toString(historiqueJ2.calculPoint());
		}
		return res;
	}

	public String getDernierCoupAdv(Integer idJoueur) {
		String res ="";
		if(joueur1.getId().equals(idJoueur)){
			res = historiqueJ2.getLastCoupString();
		}
		else if(joueur2.getId().equals(idJoueur)){
			res = historiqueJ1.getLastCoupString();
		}
		return res;
    }
	
	public void partieJouee(Joueur joueur1, Joueur joueur2){
		if (joueur1.getCoup() == joueur2.getCoup()) {
			if(joueur1.getCoup().equals(Coup.TRAHIR)) {
				joueur1.setResultat(Resultat.P);
				joueur2.setResultat(Resultat.P);
			}
			else if(joueur1.getCoup().equals(Coup.COOPERER)){
				joueur1.setResultat(Resultat.C);
				joueur2.setResultat(Resultat.C);
			}
		}
		else if ((joueur1.getCoup() == Coup.TRAHIR) && (joueur2.getCoup() == Coup.COOPERER)){
			joueur1.setResultat(Resultat.T);
			joueur2.setResultat(Resultat.D);
		}
		else {
			joueur1.setResultat(Resultat.D);
			joueur2.setResultat(Resultat.T);
		}
		
		historiqueJ1.addCoupRes(joueur1);
		historiqueJ2.addCoupRes(joueur2);
	}

    public void joueUnCoup(Integer idPartie, Integer idJoueur, String coup) {
		if(this.id.equals(idPartie)){
			if(joueur1.getId().equals(idJoueur)){
				joueur1.setCoupString(coup);
			}
			else if(joueur2.getId().equals(idJoueur)){
				joueur2.setCoupString(coup);
			}
		}
    }

	public boolean ifPlayed(){
		boolean res = false;
		if(joueur1.getCoup()!= null && joueur2.getCoup()!= null){
			res = true;
		}
		return res;
	}

	public void resetCoupJoueur(){
		joueur1.setCoup(null);
		joueur2.setCoup(null);
	}

	public void setNbTour(int nbTour){
		this.nbTour = nbTour;
	}

	public void setJoueur1(Joueur joueur){
		this.joueur1 = joueur;
	}

	public Joueur getJoueur1(){
		return this.joueur1;
	}

	public void setJoueur2(Joueur joueur){
		this.joueur2 = joueur;
	}

	public Joueur getJoueur2(){
		return this.joueur2;
	}

	public Integer getPartieId(){
		return id;
	}

	public int getNbTourJouee() {
		return nbTourJouee;
	}

    public void setNbTourJouee(int nbTourJouee) {
		this.nbTourJouee = nbTourJouee;
    }

    public int getNbTour() {
        return nbTour;
    }

	public string getNbToursString() {
		return nbTour.toString();
	}


    public void setStrategie(Integer idJoueur, int strategie) {
		if(joueur1.getId().equals(idJoueur)){
			joueur1.setStrategie(strategie);
			joueur1.setConnect(false);
		}
		else if(joueur2.getId().equals(idJoueur)){
			joueur2.setStrategie(strategie);
			joueur2.setConnect(false);
		}
    }

}


