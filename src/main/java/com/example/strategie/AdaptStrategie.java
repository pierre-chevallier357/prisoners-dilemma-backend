package com.example.strategie;
import java.util.ArrayList;

import strategie2.*;
import com.example.joueur.Coup;

public class AdaptStrategie implements Strategie {
	int strategie;

	public AdaptStrategie(int strategie) {
        this.strategie = strategie;
    }
    
    @Override
    public Coup prochainCoup(ArrayList<Coup> historiqueJ1, ArrayList<Coup> historiqueJ2) {
        Coup coup = null;
        Choix choix = null;
        ArrayList<Choix> historiqueAdapter = listAdapter(historiqueJ2);
        switch (strategie){
            case 13: 
                StrategieDonnantDonnant strat = new StrategieDonnantDonnant();
                choix = strat.jouerStrategie(historiqueAdapter, (historiqueJ2.size()));
                break;
    
            case 14: 
                try {
                    StrategieToujourTrahir strat2 = new StrategieToujourTrahir();
                    choix = strat2.jouerStrategie(historiqueAdapter, (historiqueJ2.size()));
                } catch (Exception e) {
                    choix = Choix.COOPERER;
                }
                break;
            default:
            	break;

        }
        if((choix !=null) &&choix.equals(Choix.COOPERER)){
            coup =Coup.COOPERER;
        }
        else if((choix !=null) && choix.equals(Choix.TRAHIR)){
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
