package com.example;

import com.example.partiedejeux.Jeu;

public interface GameInterface  {
    
    public String index();
    public Integer creationJoueur(String nom);
    public Integer creationPartie(Integer idJoueur, int nbTour);
    public boolean rejoindrePartie(Integer idPartie, Integer idJoueur);
    public boolean attenteDuJoueur2(Integer idPartie, Integer idJoueur);
    public String nbCoupsJoues(Integer idPartie);
    public boolean coupJoueur(Integer idPartie, Integer idJoueur, String coup);
    public boolean strategieJoueur(Integer idPartie, Integer idJoueur, int strategie);
    public boolean disconnectJoueur(Integer idPartie, Integer idJoueur);
    public boolean jouePartie(Integer idPartie,  Integer idJoueur);
    public String resultatTour(Integer idPartie, Integer idJoueur);
    public String dernierCoupAdv(Integer idPartie, Integer idJoueur);
    public String resFinal(Integer idPartie, Integer idJoueur);
    public boolean supprimerPartie(Integer idPartie);
    public String getAllJoueur();
    public String getAllIdsJoueur();
    public String getAllNomsJoueur();
    public String getAllPartie();
    public Jeu getJeu();
    
}
