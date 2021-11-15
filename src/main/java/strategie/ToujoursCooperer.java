package strategie;

import java.util.ArrayList;

import joueur.*;

public class ToujoursCooperer extends Strategie{
	@Override
	public Coup ProchainCoup(Joueur joueur, ArrayList<Coup> historiqueJ1, ArrayList<Coup> historiqueJ2) {
		return Coup.COOPERER;
	}
}
