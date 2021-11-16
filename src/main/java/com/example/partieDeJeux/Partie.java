package com.example.partieDeJeux;

import java.util.ArrayList;
import java.util.Random;

public class Partie {

    ArrayList<Jeu> PartieEncours = new ArrayList<>();
    private int id;

    public Jeu creationDePartie(){
        Random r = new Random();
        int low = 1000;
        int high = 9999;
        this.id = r.nextInt(high-low) + low;
        Jeu jeu = new Jeu(id);
        PartieEncours.add(jeu);

        return jeu;
	}
    
}
