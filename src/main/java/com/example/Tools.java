package com.example;

import java.util.ArrayList;
import java.util.Random;

import com.example.partieDeJeux.Jeu;

public class Tools {
    public static Integer randomNum(){
        Random r = new Random();
		int low = 1000;
		int high = 9999;
		return  r.nextInt(high-low) + low;
    }

    public static Jeu jeuDansList(ArrayList<Jeu> listPartie, Integer id ){
        Jeu jeu = null;
        for (Jeu j : listPartie) {
            if(j.getPartieId()==id){
              jeu = j;
            }
        }
        return jeu;
    }
}
