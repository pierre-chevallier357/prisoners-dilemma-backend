package com.example;

import java.util.ArrayList;

import com.example.joueur.Joueur;
import com.example.partiedejeux.Jeu;

public class GameRequest implements GameInterface {

    
    private ArrayList<Joueur> listJoueur = new ArrayList<>();
    private ArrayList<Jeu> listPartie = new ArrayList<>();

    @Override
    public String index() {
        return "Ceci est le serveur, pour jouer, veuillez aller sur l'application : client-pc.web.app";
    }

    @Override
    public Integer creationJoueur(String nom) {
        Joueur joueur = new Joueur();
        Integer i = Tools.randomNum();
        for (Joueur j : listJoueur) {
          while(j.getId().equals(i)){
            i = Tools.randomNum();
          }
        }
        joueur.setId(i);
        joueur.setNom(nom);
        joueur.setConnect(true);
        listJoueur.add(joueur);
        return joueur.getId();
    }

    @Override
    public Integer creationPartie(Integer idJoueur, int nbTour) {
		Joueur joueur = Tools.joueurDansList(listJoueur, idJoueur);
        Jeu jeu = new Jeu();

        jeu.setJoueur1(joueur);
        jeu.setNbTour(nbTour);
        listPartie.add(jeu);
        return jeu.getPartieId();
	}

    @Override
    public boolean rejoindrePartie(Integer idPartie, Integer idJoueur) {
        Joueur joueur = Tools.joueurDansList(listJoueur, idJoueur);
        Jeu jeu = Tools.jeuDansList(listPartie, idPartie);  
        jeu.setJoueur2(joueur);
        jeu.attenteJoueur2(idJoueur);
        return true;
    }

    @Override
    public boolean attenteDuJoueur2(Integer idPartie, Integer idJoueur) {
        Jeu jeu = Tools.jeuDansList(listPartie, idPartie);    
        return jeu.attenteJoueur2(idJoueur);
    }

    @Override
    public String nbCoupsJoues(Integer idPartie) {
        Jeu jeu = Tools.jeuDansList(listPartie, idPartie);
        String res = "";
        res += jeu.getNbTour();
        return res;
    }

    @Override
    public boolean coupJoueur(Integer idPartie, Integer idJoueur, String coup) {
        boolean res = false;
        Jeu jeu = Tools.jeuDansList(listPartie, idPartie);
        try{
          jeu.joueUnCoup(idPartie, idJoueur, coup); 
          res = jeu.attenteDeCoup();
        }catch (Exception e) {
          res = false;
        }
        return res;
    }

    @Override
    public boolean strategieJoueur(Integer idPartie, Integer idJoueur, int strategie) {
        Jeu jeu = Tools.jeuDansList(listPartie, idPartie);
        jeu.setStrategie(idJoueur, strategie);
        return true;
    }

    @Override
    public boolean disconnectJoueur(Integer idPartie, Integer idJoueur) {
        Jeu jeu = Tools.jeuDansList(listPartie, idPartie);
        jeu.setStrategie(idJoueur, 1);
        return true;
    }

    @Override
    public boolean jouePartie(Integer idPartie, Integer idJoueur) {
        boolean res = false;
        Jeu jeu = Tools.jeuDansList(listPartie, idPartie);
        if(jeu.getJoueur1().isConnect()){
          if(jeu.getPartieId().equals(idPartie) && jeu.getJoueur1().getId().equals(idJoueur)){
            jeu.jeuManche();
            jeu.resetCoupJoueur();
            res = true;
          }
        }
        else{
          jeu.jeuManche();
          jeu.resetCoupJoueur();
          res = true;
        }
        jeu.setNbTourJouee(jeu.getNbTourJouee()+1);
        if(jeu.getNbTourJouee() == jeu.getNbTour()){
          res = false;
        }
        return res;
    }

    @Override
    public String resultatTour(Integer idPartie, Integer idJoueur) {
        Jeu jeu = Tools.jeuDansList(listPartie, idPartie);
        String res = "";
        res = jeu.getRes(idJoueur);
        return res;
      }

    @Override
    public String dernierCoupAdv(Integer idPartie, Integer idJoueur) {
        Jeu jeu = Tools.jeuDansList(listPartie, idPartie);
        String res = "";
        res += jeu.getDernierCoupAdv(idJoueur);
        return res;
      }

    @Override
    public String resFinal(Integer idPartie, Integer idJoueur) {
        Jeu jeu = Tools.jeuDansList(listPartie, idPartie);
        String res = "";
        res += jeu.getRes(idJoueur)+"&"+jeu.getResAdv(idJoueur) ;
        listPartie.remove(jeu);
        return res;
      }

    @Override
    public boolean supprimerPartie(Integer idPartie) {
        Jeu jeu = Tools.jeuDansList(listPartie, idPartie);
        if(jeu !=null){
          listPartie.remove(jeu);
        }
        return true;
      }

    @Override
    public String getAllJoueur() {
        String res = "";
        for (Joueur j : listJoueur) {
          res += j.getNom()+"&"+j.getId()+"&";
        }
        return res;
    }

    @Override
    public String getAllIdsJoueur() {
        String res = "";
        for (Joueur j : listJoueur) {
          res += j.getId()+"&";
        }
        return res;
    }

    @Override
    public String getAllNomsJoueur() {
        String res = "";
        for (Joueur j : listJoueur) {
          res += j.getNom()+"&";
        }
        return res;
    }

    @Override
    public String getAllPartie() {
        String res = "";
        for (Jeu p : listPartie) {
          if(!p.getJoueur2().isConnect()){
            res += p.getPartieId()+"&";
          }
        }
        return res;
    }

    @Override
    public Jeu getJeu() {
        return listPartie.get(0);
    }
    
}
