package com.example.partieDeJeux;
import com.example.strategie.*;
import java.util.ArrayList;


import com.example.joueur.*;

public class Jeu extends Thread{
	
	Joueur joueur1 = new Joueur();
	Joueur joueur2 = new Joueur();
	Strategie strategieJ1;
	Strategie strategieJ2;
	Integer id;

	int nb_tour;
	
	HistoriqueJoueur historiqueJ1 = new HistoriqueJoueur();
	HistoriqueJoueur historiqueJ2 = new HistoriqueJoueur();
	
    ArrayList<String> RetourDesResultats = new ArrayList<String>();



	public Jeu(){
		this.id = Tools.randomNum();
	}
	
	public boolean connectionJoueur2(int id){
		if(this.id ==id){
			joueur2.setConnect(true);
			return true;
		}

		return false;
	}

	public synchronized void attenteDeCoup(Integer id){
		boolean iHaveWait = false;
		while (!ifPlayed()) {
			try {
				System.out.println("JE MENDOR "+ id);
				iHaveWait = true;
				wait();
			} catch (Exception e) {
				Thread.currentThread().interrupt();
			}
		}
		if(!iHaveWait){
			notifyAll();
			System.out.println("JE REVEILLE "+id);
		}

	}

	public void jeuManche(){
		
		partieJouee(this.joueur1, this.joueur2);

	}

	public void resetCoupJoueur(){
		joueur1.setCoup(null);
		joueur2.setCoup(null);
	}

	public void setNbTour(int nb_tour){
		this.nb_tour = nb_tour;
	}
	public ArrayList<String> renvoiString(){
		return this.RetourDesResultats;
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

	public void setStrategieJ1(Strategie strategie){
		this.strategieJ1 = strategie;
	}

	public Strategie getStrategieJ1(){
		return strategieJ1;
	}

	public Integer getPartieId(){
		return this.id;
	}

	public String getRes(Integer idJoueur){
		String res ="";
		if(joueur1.getId()== idJoueur){
			res = Integer.toString(historiqueJ1.calculPoint());
		}
		else if(joueur2.getId()== idJoueur){
			res = Integer.toString(historiqueJ2.calculPoint());
		}
		return res;
	}
	
	public void partieJouee(Joueur joueur1, Joueur joueur2){
		if (joueur1.getCoup() == joueur2.getCoup()) {
			switch (joueur1.getCoup()) {
			case TRAHIR :
				joueur1.setResultat(Resultat.P);
				joueur2.setResultat(Resultat.P);
				break;
			case COOPERER:
				joueur1.setResultat(Resultat.C);
				joueur2.setResultat(Resultat.C);
				break;
				
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

    public void JoueUnCoup(Integer idPartie, Integer idJoueur, String coup) {
		if(this.id == idPartie){
			if(joueur1.getId()== idJoueur){
				joueur1.setCoupString(coup);
			}
			else if(joueur2.getId()== idJoueur){
				joueur2.setCoupString(coup);
			}
		}
    }

	public boolean ifPlayed(){
		boolean res = false;
		if(joueur1.getCoup()!= null && joueur2.getCoup() != null){
			res = true;
		}
		return res;
	}

}


