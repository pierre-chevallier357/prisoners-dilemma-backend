package com.example.partieDeJeux;
import com.example.strategie.*;
import java.util.ArrayList;

import com.example.joueur.*;

public class Jeu extends Thread{
	
	Joueur joueur1 = new Joueur();
	Joueur joueur2 = new Joueur();
	Strategie strategieJ1;
	Strategie strategieJ2;
	int id;

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

	public void attenteDeCoup(Integer idJoueur){
		if(joueur1.getId()== idJoueur){
			while (joueur2.getCoup()== null) {
				try {
					Thread.currentThread();
					Thread.sleep(100);
				} catch (Exception e) {}
			}
			
		}
		else if(joueur2.getId()== idJoueur){
			while (joueur1.getCoup()== null) {
				try {
					Thread.currentThread();
					Thread.sleep(100);
				} catch (Exception e) {}
			}
			
		}
	}

	public void jeuManche(){
		if(joueur1.isConnect() == false) {
			
			joueur1.setCoup(strategieJ1.ProchainCoup(joueur1, historiqueJ1.getListCoup(), historiqueJ2.getListCoup()));
			joueur2.setCoup(strategieJ2.ProchainCoup(joueur2, historiqueJ2.getListCoup(), historiqueJ1.getListCoup()));
		}
		
		partieJouee(this.joueur1, this.joueur2);
		if (joueur1.isConnect()){
			joueur1.setCoup(null);
		}
		if(joueur2.isConnect()){
			joueur2.setCoup(null);
		}

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

}


