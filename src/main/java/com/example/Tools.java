package com.example;

import java.util.ArrayList;
import java.util.Random;

import com.example.joueur.Coup;
import com.example.joueur.Joueur;
import com.example.partiedejeux.HistoriqueJoueur;
import com.example.partiedejeux.Jeu;

import com.example.strategie.*;

public class Tools {
    private Tools(){}
    public static Integer randomNum(){
        Random r = new Random();
		int low = 1000;
		int high = 9999;
		return  r.nextInt(high-low) + low;
    }

    public static Jeu jeuDansList(ArrayList<Jeu> listPartie, Integer id ){
        Jeu jeu = null;
        for (Jeu j : listPartie) {
            if(j.getPartieId().equals(id)){
              jeu = j;
            }
        }
        return jeu;
    }

    public static Joueur joueurDansList(ArrayList<Joueur> listJoueur, Integer id ){
        Joueur joueur = null;
        for (Joueur j : listJoueur) {
            if(j.getId().equals(id)){
              joueur = j;
            }
        }
        return joueur;
    }

    public static Coup prochainCoupStrat(HistoriqueJoueur historiqueJ1, HistoriqueJoueur historiqueJ2, int strategie) {
        Coup coup = null;
        Strategie strat;
        switch (strategie){
            case 1: 
                strat = new Aleatoire();
                coup = strat.prochainCoup(historiqueJ1.getListCoup(), historiqueJ2.getListCoup());
                break;
            case 2: 
                strat = new Donnant2Donnant();
                coup = strat.prochainCoup(historiqueJ1.getListCoup(), historiqueJ2.getListCoup());
                break;
            case 3: 
                strat = new Donnant2DonnantAleatoire();
                coup = strat.prochainCoup(historiqueJ1.getListCoup(), historiqueJ2.getListCoup());
                break;
            case 4: 
                strat = new DonnantDonnant();
                coup = strat.prochainCoup(historiqueJ1.getListCoup(), historiqueJ2.getListCoup());
                break;
            case 5: 
                strat = new DonnantDonnantAleatoire();
                coup = strat.prochainCoup(historiqueJ1.getListCoup(), historiqueJ2.getListCoup());
                break;
            case 6: 
                strat = new PacificateurNaif();
                coup = strat.prochainCoup(historiqueJ1.getListCoup(), historiqueJ2.getListCoup());
                break;
            case 7: 
                strat = new Rancunier();
                coup = strat.prochainCoup(historiqueJ1.getListCoup(), historiqueJ2.getListCoup());
                break;
            case 8: 
                strat = new SondeurNaif();
                coup = strat.prochainCoup(historiqueJ1.getListCoup(), historiqueJ2.getListCoup());
                break;
            case 9: 
                strat = new SondeurRepentant();
                coup = strat.prochainCoup(historiqueJ1.getListCoup(), historiqueJ2.getListCoup());
                break;
            case 10: 
                strat = new ToujoursCooperer();
                coup = strat.prochainCoup(historiqueJ1.getListCoup(), historiqueJ2.getListCoup());
                break;
            case 11: 
                strat = new ToujoursTrahir();
                coup = strat.prochainCoup(historiqueJ1.getListCoup(), historiqueJ2.getListCoup());
                break;
            case 12: 
                strat = new VraiPacificateur();
                coup = strat.prochainCoup(historiqueJ1.getListCoup(), historiqueJ2.getListCoup());
                break;
            default:
                coup = AdaptStrategie.adaptCoup(historiqueJ2.getListCoup(),strategie);
                break;
        }
        return coup;
    }
}
