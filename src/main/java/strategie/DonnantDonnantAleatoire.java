package strategie;

import java.util.ArrayList;

import joueur.*;

public class DonnantDonnantAleatoire extends Strategie{
	@Override
	public Coup ProchainCoup(Joueur joueur, ArrayList<Coup> historiqueJ1, ArrayList<Coup> historiqueJ2) {
		Coup coup;
		try {
			if(Math.random()<0.6) {
				coup = historiqueJ2.get(historiqueJ2.size() - 1);
			}
			else {
				coup = CoupAleatoire(0.5);
			}
		}
		catch(Exception e){
			coup = CoupAleatoire(0.5);
		}
		return coup;
	}
}
