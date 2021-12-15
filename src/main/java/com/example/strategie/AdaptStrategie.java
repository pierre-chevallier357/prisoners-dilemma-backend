package com.example.strategie;
import java.util.ArrayList;

import strategie2.*;
import com.example.joueur.Coup;

public class AdaptStrategie {
    
    public static Coup adaptCoup(ArrayList<Coup> historique, int strategie){
        Coup coup = null;
        Choix choix = Choix.COOPERER;
        ArrayList<Choix> historiqueAdapter = listAdapter(historique);
        switch (strategie){
            case 13: 
                StrategieDonnantDonnant strat = new StrategieDonnantDonnant();
                choix = strat.jouerStrategie(historiqueAdapter, (historique.size()-1));
                break;
    
            case 14: 
                try {
                    StrategieToujourTrahir strat2 = new StrategieToujourTrahir();
                    choix = strat2.jouerStrategie(historiqueAdapter, (historique.size()-1));
                } catch (Exception e) {
                    choix = Choix.COOPERER;
                }
                break;
                
            default:
                choix = Choix.COOPERER;
                break;
        }
        if(choix.equals(Choix.COOPERER)){
            coup =Coup.COOPERER;
        }
        else if(choix.equals(Choix.TRAHIR)){
            coup = Coup.TRAHIR;
        }
        return coup;
    }

    public static ArrayList<Choix> listAdapter(ArrayList<Coup> historique){
        ArrayList<Choix> listChoix = new ArrayList<>();
        for (Coup coup : historique) {
            if(coup.equals(Coup.COOPERER)){
                listChoix.add(Choix.COOPERER);
            }
            else if(coup.equals(Coup.TRAHIR)){
                listChoix.add(Choix.TRAHIR);
            }
        }
        return listChoix;
    }
    
}
