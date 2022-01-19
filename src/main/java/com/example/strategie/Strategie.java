package com.example.strategie;

import java.util.ArrayList;

import com.example.joueur.*;

public interface Strategie {
	public abstract Coup prochainCoup(ArrayList<Coup> historiqueJ1, ArrayList<Coup> historiqueJ2);
}
