package strategie;

import java.util.ArrayList;

import joueur.*;

public class DonnantDonnant extends Strategie{
	@Override
	public Coup ProchainCoup(Joueur joueur, ArrayList<Coup> historiqueJ1, ArrayList<Coup> historiqueJ2) {
		Coup coup;
		try {
			coup =  historiqueJ2.get(historiqueJ2.size() - 1);
		}		
		catch(Exception e){
			coup = CoupAleatoire(0.5);
		}
		
		return coup;
		
	}

}
